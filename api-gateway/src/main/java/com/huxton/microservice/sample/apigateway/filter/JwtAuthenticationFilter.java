package com.huxton.microservice.sample.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthenticationFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        log.info("Custom global filter");

        log.info("Authorization: %s".formatted(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)));
        return chain.filter(exchange);
    }
}
