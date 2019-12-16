package com.example.coppolab.api_gateway;

import com.example.coppolab.api_gateway.configuration.UriConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }



    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        String application_uri = uriConfiguration.getApplication_uri();
        return builder.routes()

                .route(p -> p
                        .path("/register").and()

                        .method(HttpMethod.POST)
                        .filters(f -> f.rewritePath("/register","/user/register"))

                        .uri(application_uri)

                )

                .build();

    }

}


