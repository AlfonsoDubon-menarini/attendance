package com.attendance.infrastructure.adapter.input.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("API de Control de Asistencia Universitaria")
                .version("1.0.0")
                .description("Sistema integral para el registro de check-in y check-out de Estudiantes, " +
                        "Catedráticos y Personal Administrativo. Implementa Arquitectura Hexagonal " +
                        "y validaciones de dominio en tiempo real.")
                .contact(new Contact()
                        .name("Equipo de Desarrollo de Asistencia")
                        .email("soporte-attendance@universidad.edu")
                        .url("https://asistencia.universidad.edu"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"));

        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Servidor Local (Desarrollo)");

        Server dockerServer = new Server()
                .url("http://attendance-api:8080") // Cambiado de api-reserva
                .description("Servidor en Red Docker (Producción/QA)");

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer, dockerServer));
    }
}
