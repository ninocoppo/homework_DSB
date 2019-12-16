package com.example.coppolab.api_gateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UriConfiguration {

    private String application_uri = "http://localhost:8081";
    private String testService = "http://localhost:8081/test";

    public String getTestService() {
        return testService;
    }

    public void setTestService(String testService) {
        this.testService = testService;
    }

    public String getApplication_uri() {
        return application_uri;
    }

    public void setApplication_uri(String application_uri) {
        this.application_uri = application_uri;
    }
}
