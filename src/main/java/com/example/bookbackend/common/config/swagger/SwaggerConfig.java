package com.example.bookbackend.common.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        SecurityScheme securityScheme = new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat(jwt);

        return new OpenAPI()
                .components(new Components().addSecuritySchemes(jwt, securityScheme))
                .info(apiInfo())
                .addSecurityItem(securityRequirement);
    }
    private Info apiInfo() {
        return new Info()
                .title("book API v1") // API의 제목
                .description("Swagger UI") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
