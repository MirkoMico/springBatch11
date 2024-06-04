package com.example.MS3.integration;


import com.example.MS3.dto.EndpointDTO;
import com.example.MS3.mapper.EndpointMapper;
import com.example.MS3.model.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.DefaultFileNameGenerator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Duration;

@Component
@EnableIntegration
@IntegrationComponentScan
@RequiredArgsConstructor
public class Config {

    private final Job importEndpointInfo;

    private final JobRepository jobRepository;
    @Value("${endpoint.info.directory}")
    private String endpointDirectory;

    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlows.from(fileReadingMessageSource(), sourcePolling ->
                        sourcePolling.poller(Pollers.fixedDelay(Duration.ofSeconds(5)).maxMessagesPerPoll(1)))
                .channel(fileIn())
                .handle(fileRenameProcessingHandler())
                .transform(fileMessageJobRequest())
                .handle(jobLaunchingGateway())
                .log()
                .get();

    }


    public FileReadingMessageSource fileReadingMessageSource() {
        var messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(endpointDirectory));
       // messageSource.setFilter(new SimplePatternFileListFilter("*.csv"));
        return messageSource;
    }

    public DirectChannel fileIn() {
        return new DirectChannel();
    }

    public FileWritingMessageHandler fileRenameProcessingHandler() {
        var fileWritingMessage = new FileWritingMessageHandler(new File(endpointDirectory));
        fileWritingMessage.setFileExistsMode(FileExistsMode.REPLACE);
        fileWritingMessage.setDeleteSourceFiles(Boolean.TRUE);
        fileWritingMessage.setFileNameGenerator(new DefaultFileNameGenerator());
        fileWritingMessage.setFileNameGenerator(fileNameGenerator());
        fileWritingMessage.setRequiresReply(Boolean.FALSE);
        return fileWritingMessage;
    }

    public DefaultFileNameGenerator fileNameGenerator() {
        var fileNameGenerator = new DefaultFileNameGenerator();
        fileNameGenerator.setExpression("payload.name + '.processing'");
        return fileNameGenerator;
    }

    public FileMessageJobRequest fileMessageJobRequest() {
        var transformer = new FileMessageJobRequest();
        transformer.setJob(importEndpointInfo);
        transformer.setFileName("input.file.name");
        return transformer;
    }

    public JobLaunchingGateway jobLaunchingGateway() {
        var simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository);
        simpleJobLauncher.setTaskExecutor(new SyncTaskExecutor());
        return new JobLaunchingGateway(simpleJobLauncher);
    }
}
