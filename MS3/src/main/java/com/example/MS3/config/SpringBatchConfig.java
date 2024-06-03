package com.example.MS3.config;

import com.example.MS3.model.Endpoint;
import com.example.MS3.repository.EndpointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EndpointRepository endpointRepository;

    @Bean
    public FlatFileItemReader<Endpoint> reader() {
        FlatFileItemReader<Endpoint> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("endpoint.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<Endpoint> lineMapper() {
        DefaultLineMapper<Endpoint> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("endpointId", "endpointPath", "endpointType");

        BeanWrapperFieldSetMapper<Endpoint> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Endpoint.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public RepositoryItemWriter<Endpoint> writer() {
        RepositoryItemWriter<Endpoint> writer = new RepositoryItemWriter<>();
        writer.setRepository(endpointRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("src/main/resources/endpoint.csv")
                .<Endpoint, Endpoint>chunk(10)
                .reader(reader())
                .writer(writer())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job runJob(){
        return jobBuilderFactory
                .get("endpoint.csv")
                .start(step1())
                .build();
    }

}
