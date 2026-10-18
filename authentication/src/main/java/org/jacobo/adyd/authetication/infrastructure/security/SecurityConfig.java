package org.jacobo.adyd.authetication.infrastructure.security;


import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(setCorsConfig()))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/swagger/**").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/**").authenticated()
                                .requestMatchers(HttpMethod.PATCH, "/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/**").authenticated())
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticator()))
                );
        return http.build();
    }

    private CorsConfigurationSource setCorsConfig() {
        val config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticator() {
        KeycloakRealmRoleConverter roleConverter = new KeycloakRealmRoleConverter();

        // Envolverlo en tu convertidor custom que genera el CustomJwtAuthenticationToken
        return new CustomJwtAuthenticationConverter(roleConverter);
    }



}
