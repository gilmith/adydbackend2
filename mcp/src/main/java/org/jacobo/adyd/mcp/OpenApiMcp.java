package org.jacobo.adyd.mcp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Slf4j
public class OpenApiMcp implements ToolCallbackProvider {

    private final String apiGatewayUrl;
    private final Map<String, BiFunction<Object, ToolContext, Object>> toolMap =
            new LinkedHashMap<>();
    private final Map<String, String> inputSchemaMap = new HashMap<>();
    private final Map<String, String> descriptionMap = new HashMap<>();

    private final RestClient restClient;

    public OpenApiMcp(
            String openApiSpecPath,
            String apiGatewayUrl,
            RestClient restClient) {

        this.apiGatewayUrl = apiGatewayUrl;
        this.restClient = restClient;

        loadOpenApi(openApiSpecPath);
    }

    private void loadOpenApi(String openApiSpecPath) {
        // ClassLoader.getResourceAsStream no admite el prefijo '/', por lo que se
        // normaliza la ruta para aceptar tanto "/openapi.yaml" como "openapi.yaml".
        String resourcePath = openApiSpecPath.startsWith("/")
                ? openApiSpecPath.substring(1)
                : openApiSpecPath;

        try (InputStream inputStream = OpenApiMcp.class.getClassLoader().getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new IllegalArgumentException(
                        "OpenAPI spec not found on classpath: " + openApiSpecPath);
            }

            String spec = new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );

            ParseOptions parseOptions = new ParseOptions();
            parseOptions.setResolve(true);
            parseOptions.setResolveFully(true);

            OpenAPI openAPI = new OpenAPIV3Parser()
                    .readContents(spec, null, parseOptions)
                    .getOpenAPI();

            if (openAPI == null) {
                throw new IllegalStateException(
                        "Failed to read OpenAPI spec from: " + openApiSpecPath);
            }

            openAPI.getPaths().forEach((path, pathItem) ->
                    pathItem.readOperationsMap().forEach((httpMethod, operation) -> {

                        // Solo se exponen operaciones de lectura. Cualquier otro
                        // metodo (POST, PUT, PATCH, DELETE) queda fuera.
                        if (httpMethod != HttpMethod.GET) {
                            return;
                        }

                        val operationId = operation.getOperationId();
                        val description = operation.getDescription() != null ? operation.getDescription() : "";

                        if (operationId == null || operationId.isBlank()) {
                            return;
                        }

                        descriptionMap.put(operationId, description);

                        List<Parameter> parameters = operation.getParameters() != null
                                ? operation.getParameters()
                                : Collections.emptyList();

                        List<String> pathParamNames = parameters.stream()
                                .filter(p -> "path".equals(p.getIn()))
                                .map(Parameter::getName)
                                .filter(Objects::nonNull)
                                .toList();

                        List<Parameter> queryParameters = parameters.stream()
                                .filter(p -> "query".equals(p.getIn()))
                                .filter(p -> p.getName() != null)
                                .toList();

                        inputSchemaMap.put(
                                operationId,
                                buildInputSchema(parameters)
                        );

                        toolMap.put(operationId, (input, context) -> {

                            Map<String, Object> args = input != null
                                    ? (Map<String, Object>) input
                                    : Collections.emptyMap();

                            log.info("MCP tool '{}' invoked with args {}", operationId, args);
                            McpToolUtils.getMcpExchange(context).ifPresent(exchange -> {
                                var client = exchange.getClientInfo();
                                log.info("MCP tool '{}' invoked by client '{}' version '{}' (session {})",
                                        operationId,
                                        client != null ? client.name() : "unknown",
                                        client != null ? client.version() : "unknown",
                                        exchange.sessionId());
                            });

                            String resolvedPath = path;

                            for (String param : pathParamNames) {
                                Object value = args.get(param);
                                if (value != null) {
                                    resolvedPath = resolvedPath.replace(
                                            "{" + param + "}",
                                            value.toString()
                                    );
                                }
                            }

                            UriComponentsBuilder uriBuilder =
                                    UriComponentsBuilder.fromUriString(apiGatewayUrl + resolvedPath);

                            for (Parameter queryParam : queryParameters) {
                                Object value = args.get(queryParam.getName());
                                if (value != null) {
                                    uriBuilder.queryParam(queryParam.getName(), value);
                                }
                            }

                            var uri = uriBuilder.build().encode().toUri();
                            log.debug("MCP tool '{}' calling GET {}", operationId, uri);

                            try {
                                ResponseEntity<String> response = restClient.get()
                                        .uri(uri)
                                        .headers(headers -> headers.setAccept(List.of(MediaType.APPLICATION_JSON)))
                                        .retrieve().toEntity(String.class);
                                log.info("MCP tool '{}' responded {} ({} chars)", operationId,
                                        response.getStatusCode(),
                                        response.getBody() != null ? response.getBody().length() : 0);
                                return response.getBody();
                            } catch (Exception e) {
                                log.warn("MCP tool '{}' failed calling {}: {}", operationId, uri, e.getMessage());
                                throw e;
                            }
                        });

                        log.debug("Registered MCP tool '{}' for GET {}", operationId, path);
                    })
            );

            log.info("OpenAPI MCP initialized: {} GET tools loaded from {}", toolMap.size(), openApiSpecPath);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load and parse OpenAPI spec", e
            );
        }
    }

    @Override
    public ToolCallback[] getToolCallbacks() {
        return toolMap.entrySet()
                .stream()
                .map(entry -> {

                    String operationId = entry.getKey();

                    String schema = inputSchemaMap.getOrDefault(
                            operationId,
                            getDefaultInputSchema()
                    );

                    String toolDescription = descriptionMap.getOrDefault(
                            operationId,
                            ""
                    );

                    ToolDefinition definition = ToolDefinition.builder()
                            .name(operationId)
                            .description(toolDescription)
                            .inputSchema(schema)
                            .build();

                    return new FunctionToolCallback(
                            definition,
                            null,
                            ResolvableType.forClass(Object.class).getType(),
                            entry.getValue(),
                            null
                    );
                })
                .toArray(ToolCallback[]::new);
    }

    private String buildInputSchema(List<Parameter> parameters) {

        List<Parameter> exposedParameters = parameters.stream()
                .filter(p -> "path".equals(p.getIn()) || "query".equals(p.getIn()))
                .filter(p -> p.getName() != null)
                .toList();

        if (exposedParameters.isEmpty()) {
            return getDefaultInputSchema();
        }

        String properties = exposedParameters.stream()
                .map(p -> "\"%s\": { \"type\": \"%s\" }"
                        .formatted(p.getName(), resolveType(p)))
                .collect(Collectors.joining(","));

        String required = exposedParameters.stream()
                .filter(p -> Boolean.TRUE.equals(p.getRequired()))
                .map(p -> "\"%s\"".formatted(p.getName()))
                .collect(Collectors.joining(","));

        return """
                {
                  "type": "object",
                  "properties": {
                    %s
                  },
                  "required": [%s]
                }
                """.formatted(properties, required);
    }

    private String resolveType(Parameter parameter) {
        if (parameter.getSchema() != null) {
            Set<?> types = parameter.getSchema().getTypes();
            if (types != null && !types.isEmpty()) {
                return String.valueOf(types.iterator().next());
            }
            if (parameter.getSchema().getType() != null) {
                return parameter.getSchema().getType();
            }
        }
        return "string";
    }

    private String getDefaultInputSchema() {
        return """
                {
                  "type": "object",
                  "properties": {}
                }
                """;
    }
}
