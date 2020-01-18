package com.coppolab.spout.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@Component
public class HttpConnectionConfig {

    @Value("${prometheus.url}")
    private String prometheus_url;

    public String doRequest(String url){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = url;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        return response.toString();

    }

    public String getPrometheus_url() {
        return prometheus_url;
    }
}
