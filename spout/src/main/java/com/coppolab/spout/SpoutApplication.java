package com.coppolab.spout;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Component
public class SpoutApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(SpoutApplication.class, args);
        SpoutApplication app = context.getBean(SpoutApplication.class);
        //app.createTopic();
        while(true){
            System.out.println("dentro il while");
            app.start();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    @Autowired
    KafkaProducerMetrics kafkaProducerMetrics;

    @Value("${kafka.broker.url}")
    private String bootstrapAddress;


    private void start(){

        try {
            this.kafkaProducerMetrics.sendMessage();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
/*
    private void createTopic(){

        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        AdminClient kafkaAdminClient = KafkaAdminClient.create(properties);
        CreateTopicsResult result = kafkaAdminClient.createTopics(
                Stream.of("foo", "bar", "baz").map(
                        name -> new NewTopic("metrics", 3, (short) 1)
                ).collect(Collectors.toList())
        );
        try {
            result.all().get();
            System.out.println("topic created");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

 */

}
