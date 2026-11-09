package tn.esprit.autoloc.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Chap 6 : question 27 - configuration OpenAPI pour la documentation
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI autolocOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("AutoLoc API")
                        .description("API REST de gestion de location de vehicules")
                        .version("1.0.0"));
    }
}
