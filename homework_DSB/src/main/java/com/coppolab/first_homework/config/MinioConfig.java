package com.coppolab.first_homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MinioConfig {

    @Value("${minio.access_key}")
    private String access_key;
    @Value("${minio.secret_key}")
    private String secret_key;
    @Value("${minio.url}")
    private String url;



    public String getAccess_key() {
        System.out.println(access_key);
        return access_key;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public String getUrl() {
        return url;
    }
}
