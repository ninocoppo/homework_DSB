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
    @Value("${my.url}")
    private String my_url;


    public String getAccess_key() {
        System.out.println(access_key);
        return access_key;
    }

    public String getMy_url() {
        return my_url;
    }



    public String getSecret_key() {
        return secret_key;
    }

    public String getUrl() {
        return url;
    }
}
