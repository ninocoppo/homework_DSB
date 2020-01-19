package com.example.coppolab.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;

public class GetBody extends ServerHttpResponseDecorator {


    public GetBody(ServerHttpResponse delegate) {
        super(delegate);
    }


}
