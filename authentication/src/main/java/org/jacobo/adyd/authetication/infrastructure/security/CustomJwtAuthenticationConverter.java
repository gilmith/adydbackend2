package org.jacobo.adyd.authetication.infrastructure.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, CustomJwtAuthenticationToken> {

    private final Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter;

    public CustomJwtAuthenticationConverter(Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter) {
        this.authoritiesConverter = authoritiesConverter;
    }

    @Override
    public CustomJwtAuthenticationToken convert(Jwt jwt) {
        // 1. Extraer los roles como GrantedAuthority
        Collection<GrantedAuthority> authorities = authoritiesConverter.convert(jwt);

        // 2. Extraer el username del token (Keycloak usa 'preferred_username')
        String username = jwt.getClaimAsString("preferred_username");
        if (username == null) {
            username = jwt.getSubject();
        }

        // 3. Mapear authorities a List<String> para tu token custom
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // 4. Devolver tu objeto personalizado
        return new CustomJwtAuthenticationToken(jwt, authorities, username, roles);
    }
}