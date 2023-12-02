package managment.customerservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    public OpenAPI openAPI(@Value("${application-version}") String springdocVersion,
                           @Value("${application-description}") String description) {
        return new OpenAPI()
                .info(new Info()
                        .title("Customer Service API")
                        .description(description)
                        .version(springdocVersion)
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0")));

    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
