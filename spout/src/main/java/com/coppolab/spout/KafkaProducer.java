package com.coppolab.spout;

import com.coppolab.spout.configuration.SpoutConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {


    static KafkaTemplate<String,String> template;

    public KafkaProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void sendMessage(){
        template.send("Nappa","dioP");
        System.out.println("Message sent");
    }
}
