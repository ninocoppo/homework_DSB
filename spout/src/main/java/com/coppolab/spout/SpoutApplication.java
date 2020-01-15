package com.coppolab.spout;

import com.coppolab.spout.configuration.HttpConnectionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class SpoutApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpoutApplication.class, args);

        /*
        for(int i = 0; i< 10; i++) {
            KafkaProducer.template.send("Nappa", "ciaociaoxx"+i);
            System.out.println("Message sent");
        }
*/
        HttpConnectionConfig httpConnectionConfig = new HttpConnectionConfig();
        String response = httpConnectionConfig.doRequest("http://localhost:9090/api/v1/query?query=http_request_timer_seconds_max");
        System.out.println(response);




    }

}
