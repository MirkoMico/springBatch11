package com.example.MS3;

import com.example.MS3.config.SpringBatchConfig;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.repeat.RepeatCallback;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.support.RepeatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.File;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@EnableBatchProcessing
@EnableScheduling
public class Ms3Application {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("myJob")
    private Job myJob;

    @Autowired
    @Qualifier("anotherJob")
    private Job anotherJob;






    public static void main(String[] args) {
        SpringApplication.run(Ms3Application.class, args);
    }

    @Scheduled(fixedRate = 5000) // Esegue il job ogni 30 secondi
    public void runMyJob() {
        try {
            String uniqueParam = UUID.randomUUID().toString();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("uniqueParam", uniqueParam)
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(myJob, jobParameters);
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                System.out.println("MyJob eseguito con successo");
            }
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 10000) // Esegue il job ogni 60 secondi
    public void runAnotherJob() {
        try {
            String uniqueParam = UUID.randomUUID().toString();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("uniqueParam", uniqueParam)
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(anotherJob, jobParameters);
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                System.out.println("AnotherJob eseguito con successo");
            }
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2); // Aumenta il pool di thread se hai più job schedulati
        scheduler.setThreadNamePrefix("scheduled-task-");
        scheduler.initialize();
        return scheduler;
    }


  /* @Scheduled(fixedRate = 8000) // Esegue il job ogni 30 secondi
    public void runJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(myJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("scheduled-task-");
        scheduler.initialize();
        return scheduler;
    }*/

	/*   @Scheduled(fixedRate = 30000) // Esegue il job ogni 30 secondi
    public void runJob() {
        try {
            String uniqueParam = UUID.randomUUID().toString(); // Genera un UUID univoco
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("uniqueParam", uniqueParam)
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(myJob, jobParameters);
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                System.out.println("Both steps executed successfully");
            }
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("scheduled-task-");
        scheduler.initialize();
        return scheduler;
    }*/


}
