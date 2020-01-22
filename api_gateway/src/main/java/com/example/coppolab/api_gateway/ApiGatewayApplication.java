package com.example.coppolab.api_gateway;


import com.example.coppolab.api_gateway.configuration.UriConfiguration;
import com.example.coppolab.api_gateway.filter.HttpRequestInfo;
import com.example.coppolab.api_gateway.filter.PayloadFilter;
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

        //PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

        System.out.println("URL: "+ uriConfiguration.getUrl());
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration, HttpRequestInfo httpRequestInfo, PayloadFilter payloadFilter) {

            try {
                return builder.routes()
                        .route(p -> p
                                .path("/test/**")

                                .filters(f -> f.setPath(uriConfiguration.getUrl() + "/test/testGateway")
                                        .filter(httpRequestInfo))
                                .uri(uriConfiguration.getUrl()))


                        // Register a user with POST command
                        .route(p -> p.path("/register/**")

                                .filters(f -> f.rewritePath("/register", "/user/register")
                                        .filter(httpRequestInfo))
                                .uri(uriConfiguration.getUrl()))
                        // Return a list of files of authenticated user



                        .route(p -> p.path("/").and().method(HttpMethod.GET)
                                .filters(f -> f.rewritePath("/", "/minio/files")
                                        .filter(httpRequestInfo))
                                .uri(uriConfiguration.getUrl()))



                        // Return the file that correspond to the id_record
                        .route(p -> p.path("/**").and().method(HttpMethod.GET)
                                .filters(f -> f.rewritePath("/(?<segment>.*)", "/record/showRecord/${segment}")
                                        .filter(httpRequestInfo))
                                .uri(uriConfiguration.getUrl()))


                        // Return the file that correspond to the id_record
                        .route(p -> p.path("/**").and().method(HttpMethod.GET)
                                .filters(f -> f.rewritePath("/(?<segment>.*)", "/record/showRecord/${segment}")
                                        .filter(httpRequestInfo))
                                .uri(uriConfiguration.getUrl()))


                        // Insert new record
                        .route(p -> p.path("/").and().method(HttpMethod.POST)
                                .and().readBody(String.class, requestBody -> {return true;})
                                .filters(f -> f.rewritePath("/", "/record/put")


                                        .filter(httpRequestInfo)
                                        .filter(payloadFilter)

                                        )

                                .uri(uriConfiguration.getUrl()))

                        // Upload file in minio

                        .route(p -> p.path("/**").and().method(HttpMethod.POST)
                                .filters(f -> f.rewritePath("/(?<segment>.*)", "/record/check/${segment}")
                                        .filter(httpRequestInfo))
                                .uri(uriConfiguration.getUrl()))

                        // Delete a file

                        .route(p -> p.path("/**").and().method(HttpMethod.DELETE)
                                .filters(f -> f.rewritePath("/(?<segment>.*)", "/minio/deleteByUserRole/${segment}")
                                        .filter(httpRequestInfo))
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


