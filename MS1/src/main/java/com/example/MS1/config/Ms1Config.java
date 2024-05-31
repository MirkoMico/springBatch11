package com.example.MS1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration

public class Ms1Config {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
