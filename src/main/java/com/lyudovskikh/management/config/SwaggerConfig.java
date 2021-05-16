package com.lyudovskikh.management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("User Management Service")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("lyudovskikh.d.s@gmail.com")
                                                .name("Lyudovskikh Dmitry")
                                )
                );
    }

}
