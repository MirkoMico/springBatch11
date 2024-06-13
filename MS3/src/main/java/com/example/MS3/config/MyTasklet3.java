package com.example.MS3.config;

import com.example.MS3.service.impl.JobTestServiceImpl;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyTasklet3 implements Tasklet {

    @Autowired
    private JobTestServiceImpl myService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        myService.check3();
        return RepeatStatus.FINISHED;
    }
}
