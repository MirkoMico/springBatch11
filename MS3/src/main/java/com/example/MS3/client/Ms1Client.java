package com.example.MS3.client;

import com.example.MS3.dto.M1Dto;
import com.example.MS3.entity.ProcessStack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Component
public class Ms1Client {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Optional<List<ProcessStack>> getProcessFromMs1() {
        ResponseEntity<List<LinkedHashMap>> response =
                restTemplate.exchange("http://MS1/baw/get-process",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<LinkedHashMap>>() {});

        List<LinkedHashMap> body = response.getBody();

        List<ProcessStack> result = objectMapper.convertValue(body, new TypeReference<List<ProcessStack>>() {});
        System.out.println(result);
        return Optional.ofNullable(result);
    }



}
