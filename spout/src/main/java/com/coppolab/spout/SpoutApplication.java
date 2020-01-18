package com.coppolab.spout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Component

public class SpoutApplication {


    static KafkaProducerMetrics kafkaProducerMetrics;

    @PostConstruct
    private static void setTemplate(){
        kafkaProducerMetrics = new KafkaProducerMetrics();

    }

    public static void main(String[] args) {
        SpringApplication.run(SpoutApplication.class, args);


        setTemplate();


        while(true) {

            try {
                kafkaProducerMetrics.sendMessage();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
