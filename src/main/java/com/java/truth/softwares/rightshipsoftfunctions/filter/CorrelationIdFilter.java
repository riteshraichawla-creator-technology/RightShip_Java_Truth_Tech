package com.java.truth.softwares.rightshipsoftfunctions.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements GlobalFilter, Ordered {

    private static final String HEADER = "X-Correlation-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String cid = Optional.ofNullable(exchange.getRequest().getHeaders().getFirst(HEADER))
                .orElse(UUID.randomUUID().toString());

        ServerHttpRequest mutated = exchange.getRequest().mutate()
                .header(HEADER, cid)
                .build();

        ServerWebExchange mutatedExchange = exchange.mutate().request(mutated).build();

        return chain.filter(mutatedExchange)
                .doOnSuccess(v -> exchange.getResponse().getHeaders().set(HEADER, cid));
    }

    @Override
    public int getOrder() { return -1; }
}
