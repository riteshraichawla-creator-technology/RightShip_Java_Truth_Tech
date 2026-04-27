package com.java.truth.softwares.rightshipsoftfunctions.controller;

import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVDashboardDto;
import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVNotificationDto;
import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVOrderDto;
import com.java.truth.softwares.rightshipsoftfunctions.dtos.UVUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final WebClient userClient;
    private final WebClient orderClient;
    private final WebClient notifClient;

    public DashboardController(WebClient.Builder builder) {
        this.userClient = builder.baseUrl("http://user-service").build();
        this.orderClient = builder.baseUrl("http://order-service").build();
        this.notifClient = builder.baseUrl("http://notification-service").build();
    }

    @GetMapping("/dashboard/{userId}")
    public Mono<UVDashboardDto> dashboard(@PathVariable String userId) {
        Mono<UVUserDto> user = userClient.get().uri("/users/{id}", userId).retrieve().bodyToMono(UVUserDto.class);
        Mono<List<UVOrderDto>> orders = orderClient.get().uri("/orders?userId={id}", userId)
                .retrieve().bodyToFlux(UVOrderDto.class).collectList();
        Mono<List<UVNotificationDto>> notifs = notifClient.get().uri("/notifications/{id}", userId)
                .retrieve().bodyToFlux(UVNotificationDto.class).collectList();

        return Mono.zip(user, orders, notifs)
                .map(t -> new UVDashboardDto(t.getT1(), t.getT2(), t.getT3()));
    }
}
