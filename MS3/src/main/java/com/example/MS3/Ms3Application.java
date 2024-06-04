package com.example.MS3;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableBatchProcessing
public class Ms3Application {

//	@Autowired
//	private JobLauncher jobLauncher;
//
//	@Autowired
//	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(Ms3Application.class, args);

	}
//	@Autowired
//	public void run() {
//
//		try {
//
//
//			JobParameters jobParameters = new JobParametersBuilder()
//					.addLong("time", System.currentTimeMillis())
//					.toJobParameters();
//			jobLauncher.run(job, jobParameters);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


}
