package com.example.MS2.service.impl;

import com.example.MS2.dto.CheckRequestDTO;
import com.example.MS2.dto.CheckResponseDTO;
import com.example.MS2.entity.JobTest;
import com.example.MS2.repository.JobTestRepository;
import com.example.MS2.service.JobTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JobTestServiceImpl implements JobTestService {
    @Autowired
    JobTestRepository jobTestRepository;

    @Override
    public CheckResponseDTO check(CheckRequestDTO request) {
        Optional<JobTest> jobTestOpt = jobTestRepository.findByProcessId(request.getProcessId());
        JobTest jobTest = new JobTest();
        CheckResponseDTO response = new CheckResponseDTO();
        if(jobTestOpt.isEmpty()){
            jobTest.setProcessId(request.getProcessId());
            jobTest.setResponseDateTime(new Date());
            jobTest.setResponseMessage("inizio processo");
            jobTestRepository.save(jobTest);
        } else {
            jobTest=jobTestOpt.get();
            Date now = new Date();
            long diff = now.getTime() - jobTest.getResponseDateTime().getTime();
            if(diff > 17000){
                response.setStatus(true);
                jobTest.setResponseMessage("fine processo");
            } else{
                jobTest.setResponseMessage("processo in corso");
            }
            jobTestRepository.save(jobTest);
        }
        response.setProcessId(request.getProcessId());
        response.setResponseMessage(jobTest.getResponseMessage());
        response.setResponseDateTime(jobTest.getResponseDateTime());
        return response;
    }
}
