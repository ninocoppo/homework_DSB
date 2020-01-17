package com.example.coppolab.api_gateway.filter;

import com.example.coppolab.api_gateway.configuration.UriConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.HashMap;

@Component
@PropertySource("classpath:application.properties")
public class PayloadFilter implements GatewayFilter, Ordered {

    UriConfiguration uriConfiguration = new UriConfiguration();
    //private final long MAX_FILE_SIZE = this.uriConfiguration.getMaxFileSize();

    @Value("${application.maximum_file_size}")
    private String max_file_size;

    long file_length;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long max_dim=Long.parseLong(max_file_size);

        String body = exchange.getAttribute("cachedRequestBodyObject");


        HashMap<String,Object> result =
                null;
        try {
            result = new ObjectMapper().readValue(body, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String filename = (String)result.get("filename");

        File file = new File("../api_gateway/storage/"+filename);
        if(!file.exists()){
            throw new RuntimeException("File doesn't exist"+filename);
        }
        System.out.println("Lenght: "+file.length());
        this.file_length = file.length();
        if(this.file_length > max_dim){
            throw new RuntimeException("File too large, maximum file size: "+this.max_file_size+"B");
        }


        return chain.filter(exchange);
    }

    public long payload_size_input(){
        return this.file_length;
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
