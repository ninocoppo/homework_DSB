package com.coppolab.first_homework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    static String access_key;
    static String secret_key;
    static String url;

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(String access_key) {
        MinioConfig.access_key = access_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        MinioConfig.secret_key = secret_key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        MinioConfig.url = url;
    }

}
