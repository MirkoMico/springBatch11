package com.example.MS1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;


@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class Ms1Application {
//todo aggiungere un container keycloak
	public static void main(String[] args) {
		SpringApplication.run(Ms1Application.class, args);
	}

}
