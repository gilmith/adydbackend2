package org.jacobo.adyd.authetication.infrastructure.security;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationService {

    private final Enforcer casbinEnforcer;

    public boolean hasPermission(String resource, String action, Object... args){
        val auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return false;
        }

        // Extrae el 'sub' (Keycloak user ID) del JWT
        String userId = extractKeycloakUserId(auth);

        // Llama a Casbin con 4 args: sub, domain, resource, action
        return casbinEnforcer.enforce(userId, "default", resource, action);
    }

    private String extractKeycloakUserId(Authentication auth) {
        // Ajusta según tu adaptador Keycloak
        if (auth instanceof CustomJwtAuthenticationToken jwtAuth) {
            // Si usas Spring Security OAuth2 Resource Server
            return jwtAuth.getToken().getSubject();
        }
        // Fallback: usa el nombre de usuario o el claim 'sub'
        return auth.getName();
    }

}
