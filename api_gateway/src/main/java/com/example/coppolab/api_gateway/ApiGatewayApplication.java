package com.example.coppolab.api_gateway;

import com.example.coppolab.api_gateway.configuration.UriConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);

        UriConfiguration uriConfiguration = new UriConfiguration();

        System.out.println("URL: "+uriConfiguration.getUrl());
    }




    @Bean

    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
        try {
            return builder.routes()
                    .route(p -> p
                            .path("/test/**")
                            .filters(f -> f.rewritePath("/test/(.*)", "/test/testGateway"))
                            .uri(uriConfiguration.getUrl()))

                    .route(p -> p.path("/register/**").and().method(HttpMethod.POST)

                            .filters(f -> f.rewritePath("/register", "/user/register"))
                            .uri(uriConfiguration.getUrl()))

                    .route(p -> p.path("/getfile/**").and().method(HttpMethod.GET)
                            .filters(f -> f.rewritePath("/getfile/(?<segment>.*)", "/record/showRecord/${segment}"))
                            .uri(uriConfiguration.getUrl()))

                    .route(p -> p.path("/getfiles/**").and().method(HttpMethod.GET)
                            .filters(f -> f.rewritePath("/getfiles", "/minio/files"))
                            .uri(uriConfiguration.getUrl()))

                    .route(p -> p.path("/postrecord/**").and().method(HttpMethod.POST)
                            .filters(f -> f.rewritePath("/postrecord", "/record/put"))
                            .uri(uriConfiguration.getUrl()))

                    .route(p -> p.path("/postminio/**").and().method(HttpMethod.POST)
                            .filters(f -> f.rewritePath("/postminio/(?<segment>.*)", "/record/check/${segment}"))
                            .uri(uriConfiguration.getUrl()))

                    .route(p -> p.path("/delete/**").and().method(HttpMethod.DELETE)
                            .filters(f -> f.rewritePath("/delete/(?<segment>.*)", "/minio/deleteByUserRole/${segment}"))
                            .uri(uriConfiguration.getUrl()))
                    .build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}


