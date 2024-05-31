package com.example.MS3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootApplication
@EnableEurekaClient
@EnableBatchProcessing
@Slf4j
public class Ms3Application {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        SpringApplication.run(Ms3Application.class, args);

    }

    @Autowired
    public void run() {
        SCHEDULER.scheduleAtFixedRate(() -> {
            try {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters();
                jobLauncher.run(job, jobParameters);
                log.info("Schedulazione programmata di {}", job);
            } catch (Exception err) {
                err.printStackTrace();
            }
        }, 0, 20, TimeUnit.SECONDS);
    }
}
