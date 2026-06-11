package com.asep.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI asepOpenApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("ASEP API")
                                .description("Architecture & Source Code Exploration Platform APIs")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Ujjval")
                                )
                );
    }
}
