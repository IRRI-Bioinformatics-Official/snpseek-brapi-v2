package org.irri.snpseek.brapi.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Extracts Keycloak realm roles from a JWT and converts them to Spring Security
 * {@link GrantedAuthority} objects with the {@code ROLE_} prefix.
 *
 * <p>Keycloak encodes realm-level roles inside the {@code realm_access.roles}
 * claim — a nested structure that Spring Security's built-in
 * {@link org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter}
 * does not handle.  This converter bridges that gap.
 *
 * <p>Example JWT fragment:
 * <pre>{@code
 * {
 *   "realm_access": {
 *     "roles": ["BRAPI_USER", "offline_access", "uma_authorization"]
 *   }
 * }
 * }</pre>
 * produces {@code [ROLE_BRAPI_USER, ROLE_offline_access, ROLE_uma_authorization]}.
 */
class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String REALM_ACCESS_CLAIM = "realm_access";
    private static final String ROLES_KEY          = "roles";
    private static final String ROLE_PREFIX        = "ROLE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap(REALM_ACCESS_CLAIM);
        if (realmAccess == null) {
            return Collections.emptyList();
        }

        Object rolesObj = realmAccess.get(ROLES_KEY);
        if (!(rolesObj instanceof List<?> rawRoles)) {
            return Collections.emptyList();
        }

        return rawRoles.stream()
                .filter(String.class::isInstance)
                .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toUnmodifiableList());
    }
}
