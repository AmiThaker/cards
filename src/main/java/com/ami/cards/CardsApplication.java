package com.ami.cards;

import com.ami.cards.dto.CardsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Cards Microservices REST API Documentation",
                description = "Card Microservices CRUD Operation to Create, Update, Fetch and Delete Card Details",
                contact = @Contact(
                        name = "Ami",
                        email="ami@gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "External Documentation for Cards Microservices REST API",
                url = "https://localhost:9000/swagger-ui.html"
        )
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardsContactInfoDTO.class})
public class CardsApplication {
    public static void main(String [] args){
        SpringApplication.run(CardsApplication.class,args);
    }
}
