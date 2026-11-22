package org.jacobo.adyd.mcp;


import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientApi {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Value("${api.version}")
    private String apiVersion;

    @Bean
    public RestClient createRestClient(OAuth2AuthorizedClientManager authorizedClientManager){
        val interceptor =
                new OAuth2ClientHttpRequestInterceptor(
                        authorizedClientManager
                );
        interceptor.setClientRegistrationIdResolver(request -> "mcp-server");
        interceptor.setPrincipalResolver(request -> null);
        return RestClient.builder()
                .baseUrl(apiBaseUrl)
                .defaultHeader("X-API-Version", apiVersion)
                .requestInterceptor(interceptor)
                .build();
    }
}