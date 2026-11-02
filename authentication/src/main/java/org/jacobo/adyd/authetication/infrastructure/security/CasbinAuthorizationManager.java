package org.jacobo.adyd.authetication.infrastructure.security;

import lombok.val;
import org.casbin.jcasbin.main.Enforcer;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CasbinAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final Enforcer enforcer;

    public CasbinAuthorizationManager(Enforcer enforcer) {
        this.enforcer = enforcer;
    }

    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Authentication auth = authentication.get();
        if (auth == null || !auth.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }

        if (isAdmin(auth)) {
            return new AuthorizationDecision(true);
        }

        val userId = extractUserId(auth);
        val sub = extractRole(auth);
        val domain = "default";
        val obj = mapUrlToResource(context.getRequest().getRequestURI());
        val act = mapMethodToAction(context.getRequest().getMethod());

        final boolean allowed = enforcer.enforce(sub, domain, obj, act);
        return new AuthorizationDecision(allowed);
    }

    private String extractRole(Authentication auth) {
        boolean hasMaster = auth.getAuthorities().stream()
                .anyMatch(granted -> "ROLE_master".equals(granted.getAuthority()));
        if (hasMaster) {
            return "master";
        }
        return "player";
    }

    @Override
    public @Nullable AuthorizationDecision authorize(Supplier<? extends @Nullable Authentication> authentication, RequestAuthorizationContext context) {
        if (authentication == null) {
            return null;
        }
        return check(() -> authentication.get(), context);
    }

    private boolean isAdmin(Authentication auth) {
        return auth.getAuthorities().stream()
                .anyMatch(granted -> "ROLE_admin".equals(granted.getAuthority()));
    }

    private String extractUserId(Authentication auth) {
        if (auth instanceof CustomJwtAuthenticationToken customToken) {
            return customToken.getToken().getSubject();
        }
        return auth.getName();
    }

    private String mapMethodToAction(String method) {
        return switch (method.toUpperCase()) {
            case "GET" -> "view";
            case "POST" -> "create";
            case "PUT", "PATCH" -> "edit";
            case "DELETE" -> "delete";
            default -> "view";
        };
    }

    private String mapUrlToResource(String uri) {
        String path = uri.startsWith("/api/") ? uri.substring("/api/".length()) : uri;
        return path.split("/")[0];
    }
}
