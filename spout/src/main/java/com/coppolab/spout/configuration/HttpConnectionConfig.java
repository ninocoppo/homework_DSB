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
import javax.xml.ws.Action;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class HttpConnectionConfig {

    @Autowired
    private Environment env;

    private String prometheus_url = env.getProperty("${prometheus.url}");



    /*
    public String doRequest(String url){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = url;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        return response.toString();

    }*/

    public String getPrometheus_url() {
        return prometheus_url;
    }
}
