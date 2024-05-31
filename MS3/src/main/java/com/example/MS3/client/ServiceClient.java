package com.example.MS3.client;

import com.example.MS3.dto.M1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<M1DTO> getBaw1() {
        return restTemplate.getForObject("http://MS1/baw/baw1", List.class);
    }
    public <T> T getEndpoint(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

}
