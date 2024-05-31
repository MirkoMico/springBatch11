package com.example.MS3.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;

public class JobCompletionNotificationListener implements org.springframework.batch.core.JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {




    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // voglio che in questo metodo, anche se il job è completo, voglio che comunque sia continui a scrivere

    }
}
