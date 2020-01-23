package com.coppolab.spout;

import com.coppolab.spout.configuration.HttpConnectionConfig;
import com.coppolab.spout.configuration.ResponseManipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;


public class KafkaProducerMetrics {

    @Autowired
    private ResponseManipulation responseManipulation;

    @Autowired
    private KafkaTemplate<String,String> template;

    @Autowired
    private HttpConnectionConfig connectionConfig;

    private int counter = 0;

    public void createTopic(String topic){

    }


    public void sendMessage(){
        String response = this.connectionConfig.doRequest();

        ArrayList<String> metrics = this.responseManipulation.manipulateRepsonse(response);


        if(this.counter < metrics.size()) {
            while (this.counter < metrics.size()) {
                System.out.println("Sending metric: "+metrics.get(counter));
                this.template.send("metrics", metrics.get(counter));
                counter++;
            }
        }else{
            System.out.println("No new metrics yet...");
        }
    }

}
