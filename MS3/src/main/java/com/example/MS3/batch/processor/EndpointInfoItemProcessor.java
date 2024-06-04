package com.example.MS3.batch.processor;

import com.example.MS3.batch.d.EndpointDTO;
import com.example.MS3.batch.mapper.EndpointInfoMapper;
import com.example.MS3.batch.model.Endpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class EndpointInfoItemProcessor implements ItemProcessor<EndpointDTO, Endpoint> {
    private final EndpointInfoMapper endpointInfoMapper;

    @Override
    public Endpoint process(EndpointDTO endpointDTO) throws Exception {
        log.info("processing the item: {}",endpointDTO.toString());
        return endpointInfoMapper.mapToEntity(endpointDTO);
    }
}