package com.example.MS3.config;

import com.example.MS3.job.EndpointItemProcessor;
import com.example.MS3.model.Endpoint;
import com.example.MS3.repository.EndpointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class SpringBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EndpointRepository endpointRepository;
    private final JobLauncher jobLauncher;

    @Bean
    public FlatFileItemReader<Endpoint> reader() {
        FlatFileItemReader<Endpoint> itemReader = new FlatFileItemReader<Endpoint>() {
            @Override
            public Endpoint read() throws Exception {
                Endpoint endpoint = super.read();
                if (endpoint != null) {
                        return read(); // Ricorsivamente leggi il prossimo record
                    }

                return endpoint;
            }
        };
        itemReader.setResource(new ClassPathResource("MS3/src/main/resources/endpoint.csv"));
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
    public EndpointItemProcessor processor() {
        return new EndpointItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<Endpoint> writer() {
        log.info("Sono nel writer");
        RepositoryItemWriter<Endpoint> writer = new RepositoryItemWriter<>();
        writer.setRepository(endpointRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("src/main/resources/endpoint.csv").<Endpoint, Endpoint>chunk(4)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .allowStartIfComplete(true)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        return taskExecutor;
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("src/main/resources/endpoint.csv")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end().build();
    }

    @Scheduled(fixedRate = 20000, initialDelay = 10000)
    public void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
            jobLauncher.run(job(), new org.springframework.batch.core.JobParameters());
    }
}
