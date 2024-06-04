package com.example.MS3.processor;

import com.example.MS3.dto.EndpointDTO;
import com.example.MS3.mapper.EndpointMapper;
import com.example.MS3.model.Endpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class EndpointItemProcessor implements ItemProcessor<EndpointDTO, Endpoint> {
    private final EndpointMapper endpointMapper;

    @Override
    public Endpoint process(EndpointDTO item) throws Exception {
        Thread.sleep(200);// maybe hitting an external api
        log.info("processing the item: {}",item.toString());
        return endpointMapper.mapToEntity(item);
    }
}