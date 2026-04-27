package com.java.truth.softwares.rightshipsoftfunctions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
class FallbackController {

    @GetMapping("/fallback/users")
    public Mono<Map<String, Object>> usersFallback() {
        return Mono.just(Map.of("message", "User service temporarily unavailable"));
    }

    @GetMapping("/fallback/orders")
    public Mono<Map<String, Object>> ordersFallback() {
        return Mono.just(Map.of("message", "Order service temporarily unavailable"));
    }
}
