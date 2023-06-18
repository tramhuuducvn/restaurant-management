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

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("http://localhost:8080"));
        return new OpenAPI()
                .servers(servers)
                .info(new Info().title("Restaurant Management Application API")
                        .description("Sample OpenAPI 3.0")
                        .contact(new Contact()
                                .email("tramhuuduc.vn@gmail.com")
                                .name("Duc 🥰"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("1.0.0"));
    }
}