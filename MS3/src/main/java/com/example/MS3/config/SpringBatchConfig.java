package com.example.MS3.config;

import com.example.MS3.client.Ms1Client;
import com.example.MS3.job.EndpointItemProcessor;
import com.example.MS3.model.Endpoint;
import com.example.MS3.repository.EndpointRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


@Configuration
@EnableBatchProcessing
@EnableScheduling
@Slf4j
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MyTasklet myTasklet;

    @Autowired
    private MyTasklet1 myTasklet1;

    @Autowired
    private MyTasklet2 myTasklet2;
    @Autowired
    private MyTasklet3 myTasklet3;

    @Autowired
    private Ms1Client ms1Client;

    @Autowired
    private JobLauncher jobLauncher;



    public SpringBatchConfig(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory
                             ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;

    }

    @Bean
    @Primary
    public Job myJob() {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .start(myStep())
                .next(myStep1())
                .next(myStep2())
                .build();
    }

    @Bean
    public Job anotherJob() {
        return jobBuilderFactory.get("anotherJob")
                .incrementer(new RunIdIncrementer())
                .start(myStep3())
                .build();
    }



    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStep")
                .tasklet(myTasklet)
                .build();
    }


    @Bean
    public Step myStep1() {
        return stepBuilderFactory.get("myStep1")
                .tasklet(myTasklet1)
                .build();
    }
    @Bean
    public Step myStep2() {
        return stepBuilderFactory.get("myStep2")
                .tasklet(myTasklet2)
                .build();
    }

    @Bean
    public Step myStep3() {
        return stepBuilderFactory.get("myStep3")
                .tasklet(myTasklet3)
                .build();
    }

 /*   @Scheduled(cron = "0 0 * * * ?") // Esegue ogni ora
    public void perform() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        JobExecution jobExecution1 = jobLauncher.run(myJob(), jobParameters);
        if (jobExecution1.getStatus() == BatchStatus.COMPLETED) {
            JobExecution jobExecution2 = jobLauncher.run(myJob1(), jobParameters);
            if (jobExecution2.getStatus() == BatchStatus.COMPLETED) {
                log.info("Both jobs executed successfully");
            }
        }
    }*/










}


