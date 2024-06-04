package com.example.MS3.integration;

import com.example.MS3.dto.EndpointDTO;
import com.example.MS3.mapper.EndpointMapper;
import com.example.MS3.model.Endpoint;
import com.example.MS3.processor.EndpointItemProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@RequiredArgsConstructor
public class EndpointJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final EndpointItemProcessor endpointItemProcessor;


    @Bean
    public Job importEndpointInfo(Step fromFileIntoDataBase){
        return jobBuilderFactory.get("importEndpointInfo")
                .incrementer(new RunIdIncrementer())
                .start(fromFileIntoDataBase)
                .build();
    }


    @Bean
    public Step fromFileIntoDataBase(ItemReader<EndpointDTO> endpointDTOItemReader){
        return stepBuilderFactory.get("fromFileIntoDatabase")
                .<EndpointDTO, Future<Endpoint>>chunk(100)
                .reader(endpointDTOItemReader)
                .processor(asyncItemProcessor())
                .writer(asyncItemWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<EndpointDTO> endpoitInfoFileReader(@Value("#{jobParameters['input.file.name']}") String resource){
        return new FlatFileItemReaderBuilder<EndpointDTO>()
                .resource(new FileSystemResource(resource))
                .name("endpoitInfoFileReader")
                .delimited()
                .delimiter(",")
                .names(new String[]{"endpointId", "endpointPath", "endpointType", "endpointDate"})
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

    @Bean
    public TaskExecutor taskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(15);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("Thread N-> :");
        return executor;
    }

    @Bean
    public AsyncItemProcessor<EndpointDTO, Endpoint> asyncItemProcessor(){
        var asyncItemProcessor = new AsyncItemProcessor<EndpointDTO,Endpoint>();
        asyncItemProcessor.setDelegate(endpointItemProcessor);
        asyncItemProcessor.setTaskExecutor(taskExecutor());
        return asyncItemProcessor;
    }

    @Bean
    public AsyncItemWriter<Endpoint> asyncItemWriter(){
        var asyncWriter = new AsyncItemWriter<Endpoint>();
        asyncWriter.setDelegate(endpointInfoItemWriter());
        return asyncWriter;
    }
}