package com.example.MS3.client;

import com.example.MS3.dto.M1Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Ms1Client {
    @Autowired
    private RestTemplate restTemplate;

    public List<M1Dto> getProcess() {
        return restTemplate.getForObject("http://MS1/baw/get-process", List.class);
    }

}
