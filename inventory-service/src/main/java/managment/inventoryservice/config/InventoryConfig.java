package managment.inventoryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
public class InventoryConfig {
    @Bean
    public OpenAPI openAPI(@Value("${application-version}") String springdocVersion,
                           @Value("${application-description}") String description) {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Service API")
                        .description(description)
                        .version(springdocVersion)
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0")));

    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ClientResponse {
        String value() default "";
    }
}
