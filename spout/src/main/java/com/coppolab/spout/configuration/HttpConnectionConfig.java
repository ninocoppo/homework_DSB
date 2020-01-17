package com.coppolab.spout.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class HttpConnectionConfig {



    public String doRequest(String url){

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = url;
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        return response.toString();

    }


}
