package fr.ttl.atlasdd.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Atlas D&D API")
                        .version("1.0.0")
                        .description("API documentation for Atlas D&D application"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan(
                        "fr.ttl.atlasdd.controller",
                        "fr.ttl.atlasdd.apidto",
                        "fr.ttl.atlasdd.entity"
                )
                .pathsToMatch(
                        "/campaigns/**",
                        "/campaign-notes/**",
                        "/custom/characters/**",
                        "/ogl5/characters/**",
                        "/character-notes/**",
                        "/character-creation-options/**",
                        "/friends-invitations/**",
                        "/users/**"
                )
                .build();
    }
}


