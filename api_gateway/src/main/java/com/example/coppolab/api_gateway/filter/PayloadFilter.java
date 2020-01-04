package com.example.coppolab.api_gateway.filter;

import com.example.coppolab.api_gateway.configuration.UriConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.HashMap;

@Component
public class PayloadFilter implements GatewayFilter, Ordered {

    UriConfiguration uriConfiguration = new UriConfiguration();
    private final long MAX_FILE_SIZE = this.uriConfiguration.getMaxFileSize();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        String body = exchange.getAttribute("cachedRequestBodyObject");


        HashMap<String,Object> result =
                null;
        try {
            result = new ObjectMapper().readValue(body, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String filename = (String)result.get("filename");

        File file =new File("../homework_DSB/storage/"+filename);
        if(!file.exists()){
            throw new RuntimeException("File doesn't exist");
        }
        System.out.println("Lenght: "+file.length());
        long file_length = file.length();
        if(file_length > MAX_FILE_SIZE){
            throw new RuntimeException("File too large, maximum file size: "+this.MAX_FILE_SIZE+"B");
        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
