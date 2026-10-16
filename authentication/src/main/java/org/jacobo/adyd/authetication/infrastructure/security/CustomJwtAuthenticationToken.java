package org.jacobo.adyd.authetication.infrastructure.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {

    private final String username;
    private final List<String> roles;

    public CustomJwtAuthenticationToken(
            Jwt jwt,
            Collection<? extends GrantedAuthority> authorities,
            String username,
            List<String> roles
    ) {
        super(jwt, authorities, username);
        this.username = username;
        this.roles = roles;
        setAuthenticated(true);
    }



}