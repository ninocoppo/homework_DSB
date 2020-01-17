package com.example.coppolab.api_gateway.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "application")
public class UriConfiguration {



    static String url;
    static long maximum_file_size;


    public long getMaxFileSize() {
        return maximum_file_size;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maximum_file_size = maxFileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
