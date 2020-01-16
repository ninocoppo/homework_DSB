package com.example.coppolab.api_gateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;

@Component
public class MinioObjectUrlFilter implements GatewayFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("First filter"+exchange.getRequest().toString());

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {

                    HttpResponse response = (HttpResponse)exchange.getResponse();

                    System.out.println("Response: "+ response.body().toString());

                }));


    }

    @Override
    public int getOrder() {
        return 0;
    }
}
