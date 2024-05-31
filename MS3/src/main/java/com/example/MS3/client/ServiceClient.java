package com.example.MS3.client;

import com.example.MS3.dto.M1Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ServiceClient {

    @Autowired
    private RestTemplate restTemplate;


   // public String getEndpoint() {
     //   return restTemplate.postForObject("http://localhost:8080/baw/baw1", String.class);
   // }


    public List<M1Dto> getBaw1(){
        return restTemplate.getForObject("http://MS1/baw/baw1", List.class);
    }
    public <T> T getEndpoint(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    //mi fai il il metodo per prendere la chiamata post /baw/baw1


}
