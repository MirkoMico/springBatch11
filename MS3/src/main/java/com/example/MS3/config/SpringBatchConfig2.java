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
public class SpringBatchConfig2 {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    private MyTasklet2 myTasklet2;

    @Autowired
    private Ms1Client ms1Client;


    public SpringBatchConfig2(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;

    }


    @Bean
    public Job myJob2() {
        return jobBuilderFactory.get("myJob2")
                .start(myStep2())
                .build();
    }



    @Bean
    public Step myStep2() {
        return stepBuilderFactory.get("myStep2")
                .tasklet(myTasklet2)
                .build();
    }









}



