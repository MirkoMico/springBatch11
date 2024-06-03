package com.example.MS3;

import com.example.MS3.config.SpringBatchConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobParametersNotFoundException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableEurekaClient
@EnableBatchProcessing
@EnableScheduling
public class Ms3Application {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;



	public static void main(String[] args)  {
		SpringApplication.run(Ms3Application.class, args);

	}
//	@Autowired
//	@Scheduled(fixedRate = 20000)
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
