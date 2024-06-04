//package com.example.MS3.integration;
//
//import lombok.Setter;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.integration.launch.JobLaunchRequest;
//import org.springframework.batch.integration.launch.JobLaunchingGateway;
//import org.springframework.integration.annotation.Transformer;
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.util.Date;
//
//@Component
//@Setter
//public class FileMessageJobRequest {
//    private Job job;
//    private String fileName = "input.file.name";
//
//    @Transformer
//    public JobLaunchRequest jobLaunchRequest(Message<File>fileMessage){
//        var jobParameters = new JobParametersBuilder();
//        jobParameters.addString(fileName,fileMessage.getPayload().getAbsolutePath());
//        jobParameters.addDate("uniqueness", new Date());
//        return new JobLaunchRequest(job, jobParameters.toJobParameters());
//
//    }
//
//
//}
