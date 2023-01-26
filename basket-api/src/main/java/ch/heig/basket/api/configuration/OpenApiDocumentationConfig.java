package ch.heig.basket.api.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiDocumentationConfig {

    @Bean
    public OpenAPI springQuotesOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Basket API")
                        .description("Basket sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }

}