package com.example.coppolab.api_gateway.filter;

import com.example.coppolab.api_gateway.configuration.UriConfiguration;
import com.example.coppolab.api_gateway.context.Metrics;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class HttpRequestInfo implements GatewayFilter, Ordered {

    @Autowired
    private Metrics metrics;


    UriConfiguration uriConfiguration = new UriConfiguration();


    String url = uriConfiguration.getUrl();
    String method = "";
    String path = "";
    String uri = "";
    String status_code="";
    String req_outcome="";
    String request_response_time="";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //Pre filter, acts on the request to the service
        this.method = exchange.getRequest().getMethod().toString();
        this.path = exchange.getRequest().getPath().toString();
        this.uri = this.url + this.path;
        System.out.println("uri: " + this.uri + " method " + this.method);


        //Instantiate the timer to measure the http request's infos
        //Starts the timer. The time of the http request is measured by system time
        Timer.Sample sample = Timer.start();

        return chain.filter(exchange)
        //Post Filter, acts on the response from the service
        .then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            System.out.println("Status response code: " + response.getStatusCode());
            this.status_code = response.getStatusCode().toString();
            this.req_outcome = response.getStatusCode().getReasonPhrase();


            this.metrics.getRegistry().config().commonTags();
            //Stop the timer and add the timer to the registry
            sample.stop(this.metrics.getRegistry().timer("http.request.timer",
                    "Routed.uri",this.uri,
                    "http.method",this.method,
                    "response", status_code,
                    "outcome",req_outcome));
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
