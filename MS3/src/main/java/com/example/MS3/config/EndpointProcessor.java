package com.example.MS3.config;

import com.example.MS3.model.Endpoint;
import org.springframework.batch.item.ItemProcessor;

public class EndpointProcessor implements ItemProcessor<Endpoint,Endpoint> {
    @Override
    public Endpoint process(Endpoint endpoint) throws Exception {
        return endpoint;
    }
}
