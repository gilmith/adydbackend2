package org.jacobo.adyd.mcp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OpenApiMcpConfiguration {

    @Value("${openapi.spec.path}")
    private String openApiSpecPath;
    @Value("${openapi.url}")
    private String apiGatewayUrl;


    @Bean
    public OpenApiMcp getOpenApiMcp(RestClient restClient){
        return new OpenApiMcp(openApiSpecPath,
                apiGatewayUrl,
                restClient);
    }

}
