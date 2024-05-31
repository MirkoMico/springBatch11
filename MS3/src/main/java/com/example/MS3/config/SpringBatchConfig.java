package com.example.MS3.config;

import com.example.MS3.job.EndpointItemProcessor;
import com.example.MS3.model.Endpoint;
import com.example.MS3.repository.EndpointRepository;
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
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private EndpointRepository endpointRepository;

    public SpringBatchConfig(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory,
                             EndpointRepository endpointRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.endpointRepository = endpointRepository;
    }


    @Bean
    public FlatFileItemReader<Endpoint> reader() {
        FlatFileItemReader<Endpoint> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("endpoint.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);

        DefaultLineMapper<Endpoint> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("endpointId", "endpointPath", "endpointType", "endpointDate");

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new EndpointFieldSetMapper()); // Usa il nuovo mapper personalizzato

        itemReader.setLineMapper(lineMapper);

        return itemReader;
    }

    private LineMapper<Endpoint> lineMapper() {
        DefaultLineMapper<Endpoint> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("endpointId", "endpointPath", "endpointType", "endpointDate");

        BeanWrapperFieldSetMapper<Endpoint> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Endpoint.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }
    @Bean
    public EndpointItemProcessor processor() {
        return new EndpointItemProcessor();
    }
    @Bean
    public RepositoryItemWriter<Endpoint> writer() {
        RepositoryItemWriter<Endpoint> writer = new RepositoryItemWriter<>();
        writer.setRepository(endpointRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("endpoint.csv").<Endpoint, Endpoint>chunk(4)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                //.taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob() {

        
            return jobBuilderFactory
                    .get("endpoint.csv")
                    .start(step1())
                    .build();




    }

    //@Bean
    //public TaskExecutor taskExecutor() {
    //  SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
    //  asyncTaskExecutor.setConcurrencyLimit(10);
    //  return asyncTaskExecutor;
    //}
}