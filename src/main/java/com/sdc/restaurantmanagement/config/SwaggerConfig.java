package com.sdc.restaurantmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * The responsibility of this class is to configure swagger
 * This is an important part of write the API specification document
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("http://localhost:8080"));
        return new OpenAPI()
                .servers(servers)
                .info(new Info().title("Restaurant Management Application API")
                        .description("Restful API for Restaurant Management Application")
                        .contact(new Contact()
                                .email("tramhuuduc.vn@gmail.com")
                                .name("Duc Tram Huu"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("1.0.0"));
    }
}