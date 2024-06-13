package com.example.MS3.config;

import com.example.MS3.client.Ms1Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableBatchProcessing
@EnableScheduling
@Slf4j
public class SpringBatchConfig3 {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    private MyTasklet3 myTasklet3;
    @Autowired
    private Ms1Client ms1Client;


    public SpringBatchConfig3(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;

    }


    @Bean
    public Job myJob3() {
        return jobBuilderFactory.get("myJob3")
                .start(myStep3())
                .build();
    }


    @Bean
    public Step myStep3() {
        return stepBuilderFactory.get("myStep3")
                .tasklet(myTasklet3)
                .build();
    }









}




