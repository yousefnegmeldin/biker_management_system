package com.brightskies.biker_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi(){
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("dev");

        Contact contact = new Contact();

        contact.setEmail("yousefnegm@gmail.com");
        contact.setName("yousef negm");

        Info information = new Info()
                .title("Biker Management System")
                .version("1.0")
                .description("System for managing bikers, deliveries, products, stores, and stock.")
                .contact(contact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}



