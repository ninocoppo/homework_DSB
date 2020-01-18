package com.coppolab.spout;

import com.coppolab.spout.configuration.HttpConnectionConfig;
import com.coppolab.spout.configuration.SpoutConfiguration;
import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class KafkaProducerMetrics {

    @Autowired
    KafkaTemplate<String,String> template;

    @Autowired
    HttpConnectionConfig httpConnectionConfig;

    @Autowired
    SpoutConfiguration spoutConfiguration;

    @PostConstruct
    public void sendMessage(){

        System.out.println("Prom url: "+this.httpConnectionConfig.getPrometheus_url());

        String response = httpConnectionConfig.doRequest(this.httpConnectionConfig.getPrometheus_url()+"/api/v1/query?query=http_request_timer_seconds_max");
        System.out.println("SERVER response: "+response);
        this.template.send("Metrics", response);
        System.out.println("Message sent");
    }

}
