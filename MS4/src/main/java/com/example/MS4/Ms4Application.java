package com.example.MS4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Ms4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ms4Application.class, args);
	}

}
