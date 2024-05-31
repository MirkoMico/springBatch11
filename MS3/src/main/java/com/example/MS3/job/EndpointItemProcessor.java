package com.example.MS3.job;

import com.example.MS3.model.Endpoint;
import org.springframework.batch.item.ItemProcessor;

public class EndpointItemProcessor implements ItemProcessor<Endpoint, Endpoint> {
    @Override //Scelgo cosa ritorna nel csv
    public Endpoint process(Endpoint endpoint) throws Exception {
        return endpoint;
    }
}
