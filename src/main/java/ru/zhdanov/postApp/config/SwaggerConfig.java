package ru.zhdanov.postApp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springPostOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Post API Docs")
                        .description("Post REST API Documintation")
                        .version("v1.0.0"));
    }
}
