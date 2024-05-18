package managment.productservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public OpenAPI openAPI(@Value("${application-version}") String springdocVersion,
                           @Value("${application-description}") String description) {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .description(description)
                        .version(springdocVersion)
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0")));

    }
}
