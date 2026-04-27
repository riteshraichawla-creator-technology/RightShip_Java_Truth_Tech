package com.java.truth.softwares.rightshipsoftfunctions.config;

import com.java.truth.softwares.rightshipsoftfunctions.helpers.UVOceanWavesEmailSanitizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFluxSecurity
public class UVOceanWavesMicroserviceConfig {

    // A simple custom bean to demonstrate Spring Beans
    @Bean
    public UVOceanWavesEmailSanitizer emailSanitizer() {
        return email -> email == null ? null : email.trim().toLowerCase();
    }


    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex
                        .pathMatchers("/actuator/**", "/fallback/**").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }


    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }



}
