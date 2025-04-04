package com.ahason.sso.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI ahasonOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Ahason SSO Auth API")
                .version("1.0.0")
                .description("OAuth2 + OTP based SSO Authentication Server"));
    }
}
