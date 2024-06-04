package com.example.MS3.batch;

import com.example.MS3.batch.d.EndpointDTO;
import com.example.MS3.batch.processor.EndpointInfoItemProcessor;
import com.example.MS3.batch.model.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class EndpointInfoJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final EndpointInfoItemProcessor endpointInfoItemProcessor;


    @Bean
    public Job importSalesInfo(){
        return jobBuilderFactory.get("importSalesInfo")
                .incrementer(new RunIdIncrementer())
                .start(fromFileIntoDataBase())
                .build();
    }


    @Bean
    public Step fromFileIntoDataBase(){
        return stepBuilderFactory.get("fromFileIntoDatabase")
                .<EndpointDTO, Endpoint>chunk(10)
                .reader(endpointInfoFileReader())
                .processor(endpointInfoItemProcessor)
                .writer(endpointInfoItemWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<EndpointDTO> endpointInfoFileReader(){
        return new FlatFileItemReaderBuilder<EndpointDTO>()
                .resource(new ClassPathResource("endpoint.csv"))
                .name("endpointInfoFileReader")
                .delimited()
                .delimiter(",")
                .names(new String[]{"product","seller","sellerId","price","city","category"})
                .linesToSkip(1)
                .targetType(EndpointDTO.class)
                .build();
    }

    @Bean
    public JpaItemWriter<Endpoint> endpointInfoItemWriter(){
        return new JpaItemWriterBuilder<Endpoint>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

}