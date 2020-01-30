package com.coppolab.spout;

import com.coppolab.spout.configuration.HttpConnectionConfig;
import com.coppolab.spout.configuration.ResponseManipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class KafkaProducerMetrics {

    @Autowired
    private ResponseManipulation responseManipulation;

    @Autowired
    private KafkaTemplate<String,String> template;

    @Autowired
    private HttpConnectionConfig connectionConfig;

    private int counter_metrics = 0;



    public void createTopic(String topic){

    }


    public void sendMessage() {


            String response = this.connectionConfig.doRequest();

            ArrayList<String> metrics = new ArrayList<>();
            Map<String, String> metricsMap = this.responseManipulation.manipulateRepsonse(response);

                if (metricsMap != null) {
                    Map<String, String> sortedMap = new TreeMap<String, String>(metricsMap);

                    //System.out.println("DISORDINATA: "+metricsMap.toString());
                    //System.out.println("ORDINATA: "+sortedMap.toString());
                    for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
                        metrics.add(entry.getValue());
                    }

                if (this.counter_metrics < metrics.size()) {

                    while (this.counter_metrics < metrics.size()) {
                        System.out.println("Sending metric: " + metrics.get(counter_metrics));
                        this.template.send("metrics", metrics.get(counter_metrics));
                        counter_metrics++;
                    }
                } else {
                    System.out.println("No new metrics yet...");
                }
            }


    }
}
