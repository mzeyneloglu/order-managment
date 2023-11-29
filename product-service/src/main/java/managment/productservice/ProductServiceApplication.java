package managment.productservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {
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


	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
