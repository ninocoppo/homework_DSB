package com.coppolab.spout;

import com.coppolab.spout.configuration.HttpConnectionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Component
public class SpoutApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpoutApplication.class, args);


        HttpConnectionConfig httpConnectionConfig = new HttpConnectionConfig();

        while(true) {
            String response = httpConnectionConfig.doRequest("http://localhost:9090/api/v1/query?query=http_request_timer_seconds_max");
            System.out.println("SERVER response: "+response);
            KafkaProducer.template.send("Metrics", response);
            System.out.println("Message sent");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
