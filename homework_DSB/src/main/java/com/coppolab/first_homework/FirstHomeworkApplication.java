package com.coppolab.first_homework;

import com.coppolab.first_homework.config.MinioConfig;
import com.coppolab.first_homework.services.MinioService;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MinioConfig.class)
public class FirstHomeworkApplication {

    static MinioService minioService;


    public static void main(String[] args) {
        SpringApplication.run(FirstHomeworkApplication.class, args);
        /*try {
            minioService = new MinioService();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        }*/



    }





}


