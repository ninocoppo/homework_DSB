package com.example.coppolab.api_gateway.context;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Metrics {
    //Declare the register to collect metrics
    private MeterRegistry registry;

    @Autowired
    public void setRegistry(MeterRegistry registry) {
        this.registry = registry;
    }

    public MeterRegistry getRegistry() {
        return registry;
    }

}
