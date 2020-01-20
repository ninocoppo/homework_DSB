package com.coppolab.spout;

import com.coppolab.spout.configuration.HttpConnectionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;


public class KafkaProducerMetrics {


    @Autowired
    private KafkaTemplate<String,String> template;

    @Autowired
    private HttpConnectionConfig connectionConfig;

    public void createTopic(String topic){

    }


    public void sendMessage(){
        String response = this.connectionConfig.doRequest();
        System.out.println("Response: "+response);
        this.template.send("metrics", response);
        System.out.println("Message sent");
    }

}
