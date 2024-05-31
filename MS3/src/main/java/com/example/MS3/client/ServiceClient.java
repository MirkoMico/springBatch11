package com.example.MS3.client;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ServiceClient {
    private RestTemplate restTemplate;

    public <T> T getEndpoint(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

}
