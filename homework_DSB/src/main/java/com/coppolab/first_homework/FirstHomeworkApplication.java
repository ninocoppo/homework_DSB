package com.coppolab.first_homework;

import com.coppolab.first_homework.config.MinioConfig;
import com.coppolab.first_homework.services.MinioService;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class FirstHomeworkApplication {



    public static void main(String[] args) {
        SpringApplication.run(FirstHomeworkApplication.class, args);


    }





}


