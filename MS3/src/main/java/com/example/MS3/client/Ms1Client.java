package com.example.MS3.client;


import com.example.MS3.dto.M1Dto;
import com.example.MS3.entity.ProcessStack;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
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

   /* public List<M1Dto> getProcess(){
        return restTemplate.getForObject("http://MS1/baw/get-process", List.class);
    }*/

    public Optional<List<ProcessStack>> getEntitiesFromM1Service() {
        ResponseEntity<List<LinkedHashMap>> result =
                restTemplate.exchange("http://MS1/baw/get-process",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<LinkedHashMap>>() {
                        });
        List<LinkedHashMap> list = result.getBody();
        List<ProcessStack> result2 = objectMapper.convertValue(list, new TypeReference<List<ProcessStack>>() {
        });
        System.out.println(result +" risultato da m1");
        return Optional.ofNullable(result2);

    }




}


