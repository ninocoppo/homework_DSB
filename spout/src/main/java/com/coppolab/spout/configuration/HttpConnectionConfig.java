package com.coppolab.spout.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class HttpConnectionConfig {

    @Value("${prometheus.url}")
    private String prom_url;

    public String doRequest(){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = this.prom_url+"/api/v1/query?query=http_request_timer_seconds_max";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        return response.toString();

    }

}
