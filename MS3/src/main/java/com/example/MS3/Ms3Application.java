package com.example.MS3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Ms3Application {

	public static void main(String[] args) {
		SpringApplication.run(Ms3Application.class, args);
	}

}
