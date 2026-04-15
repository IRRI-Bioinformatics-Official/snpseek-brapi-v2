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
 *   <li>{@code /brapi/v2/search/variants/**} — requires BRAPI_USER Keycloak realm role.</li>
 *   <li>{@code /brapi/v2/genotyping/**} — requires BRAPI_USER Keycloak realm role.</li>
 *   <li>{@code /swagger-ui/**}, {@code /v3/api-docs/**} — public (Swagger UI).</li>
 *   <li>All other paths — publicly accessible.</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String GENOTYPING_PATH = "/brapi/v2/genotyping/**";
    private static final String SEARCH_PATH     = "/brapi/v2/search/variants/**";
    private static final String BRAPI_USER_ROLE = "BRAPI_USER";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // REST API — no CSRF tokens, no server-side sessions.
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
                // Swagger UI and OpenAPI spec — always public.
                .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs",
                        "/v3/api-docs/**"
                ).permitAll()
                // Genotyping endpoints require the Keycloak realm role BRAPI_USER.
                // hasRole() automatically prepends "ROLE_", matching what
                // KeycloakRoleConverter produces ("ROLE_BRAPI_USER").
                .requestMatchers(GENOTYPING_PATH).hasRole(BRAPI_USER_ROLE)
                .requestMatchers(SEARCH_PATH).hasRole(BRAPI_USER_ROLE)
                // BrAPI server-info and other public endpoints are left open.
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
        converter.setPrincipalClaimName("preferred_username");
        return converter;
    }
}
