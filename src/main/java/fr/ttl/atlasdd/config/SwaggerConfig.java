package fr.ttl.atlasdd.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch(
                        "/campaigns/**",
                        "/campaign-notes/**",
                        "/custom/characters/**",
                        "/ogl5/characters/**",
                        "/character-notes/**",
                        "/friends-invitations/**",
                        "/users/**")
                .build();
    }
}

