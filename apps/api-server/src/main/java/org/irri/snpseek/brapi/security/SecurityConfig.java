package org.irri.snpseek.brapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 6 configuration for the BrAPI resource server.
 *
 * <p>Token validation is delegated entirely to Spring Security's JWT support:
 * on startup the server contacts the Keycloak OIDC discovery endpoint
 * ({@code issuer-uri}/.well-known/openid-configuration) to fetch the JWKS URI,
 * then verifies every inbound Bearer token's signature, issuer, and expiry
 * automatically.
 *
 * <p>Authorization rules:
 * <ul>
 *   <li>{@code /brapi/v2/genotyping/**} — requires a valid JWT <em>and</em>
 *       the Keycloak realm role {@code BRAPI_USER}.</li>
 *   <li>All other paths — publicly accessible (BrAPI discovery, server-info, etc.).</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String GENOTYPING_PATH   = "/brapi/v2/genotyping/**";
    private static final String SEARCH_PATH       = "/brapi/v2/search/variants/**";
    private static final String BRAPI_USER_ROLE   = "BRAPI_USER";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // REST API — no CSRF tokens, no server-side sessions.
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
                // Genotyping endpoints require the Keycloak realm role BRAPI_USER.
                // hasRole() automatically prepends "ROLE_", matching what
                // KeycloakRoleConverter produces ("ROLE_BRAPI_USER").
                .requestMatchers(GENOTYPING_PATH).hasRole(BRAPI_USER_ROLE)
                .requestMatchers(SEARCH_PATH).hasRole(BRAPI_USER_ROLE)
                // BrAPI server-info, token-handling, and other public endpoints
                // are left open.  Tighten here as additional endpoints are added.
                .anyRequest().permitAll()
            )

            // Configure this server as a JWT-based OAuth2 resource server.
            // The issuer-uri in application.yml drives automatic JWKS discovery.
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(keycloakJwtConverter()))
            );

        return http.build();
    }

    /**
     * Wires {@link KeycloakRoleConverter} into Spring Security's JWT pipeline
     * so that {@code realm_access.roles} are mapped to {@link
     * org.springframework.security.core.GrantedAuthority} objects.
     */
    @Bean
    public JwtAuthenticationConverter keycloakJwtConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        // Use the "preferred_username" claim as the principal name so that logs
        // and audit entries show a human-readable identity instead of the subject UUID.
        converter.setPrincipalClaimName("preferred_username");
        return converter;
    }
}
