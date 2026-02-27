package com.attendance.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${app.gateway.base-url}")
    private String baseUrl;

    @Bean
    public RestClient gatewayRestClient() {
        // Usamos el builder estático para ser 100% independientes
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
