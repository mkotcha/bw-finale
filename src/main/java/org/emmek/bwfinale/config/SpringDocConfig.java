package org.emmek.bwfinale.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Value("${OPENAPI_TOKEN}")
    private String token;

    @Bean
    public OpenAPI customOpenAPI() {


        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(token,
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
