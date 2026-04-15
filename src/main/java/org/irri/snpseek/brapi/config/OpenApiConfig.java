package org.irri.snpseek.brapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures the OpenAPI 3.0 specification exposed at {@code /v3/api-docs}
 * and the Swagger UI served at {@code /swagger-ui.html}.
 *
 * <p>A global Bearer-token security scheme is registered so that the
 * Swagger UI "Authorize" button accepts a Keycloak JWT directly.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI brapiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SNP-Seek BrAPI v2.1")
                        .description("""
                                BrAPI v2.1 REST API for the SNP-Seek rice genomics platform at IRRI.

                                Exposes SNP variant metadata from PostgreSQL and genotype data from HDF5 files.
                                Protected endpoints require a Keycloak JWT with the **BRAPI_USER** realm role.
                                Click **Authorize** and paste your Bearer token to authenticate.
                                """)
                        .version("2.1")
                        .contact(new Contact()
                                .name("IRRI Bioinformatics")
                                .email("l.h.barboza@cgiar.org")
                                .url("https://snp-seek.irri.org")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Keycloak JWT with BRAPI_USER realm role. " +
                                                "Obtain via: POST /auth/realms/snpseek_realm/protocol/openid-connect/token")));
    }
}
