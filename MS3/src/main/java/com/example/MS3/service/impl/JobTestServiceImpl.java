package com.example.MS3.service.impl;

import com.example.MS3.client.Ms2Client;
import com.example.MS3.dto.CheckRequestDTO;
import com.example.MS3.dto.CheckResponseDTO;
import com.example.MS3.entity.JobTest;
import com.example.MS3.entity.ProcessStack;
import com.example.MS3.repository.JobTestRepository;
import com.example.MS3.repository.ProcessStackRepository;
import com.example.MS3.service.JobTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JobTestServiceImpl implements JobTestService {
    @Autowired
    private ProcessStackRepository processStackRepository;

    @Autowired
    private Ms2Client ms2Client;

    @Autowired
    private JobTestRepository jobTestRepository;

    @Override
    public void check() throws Exception {
        // rilevo il processo attivo (active = 1 and dateEnd = null)
        Optional<ProcessStack> activeProcessOpt = processStackRepository.findFirstByActiveAndDateEnd((byte) 1,null);
        ProcessStack activeProcess = null;
        CheckResponseDTO response = new CheckResponseDTO();
        // se il processo non esite seleziono la prima riga con active = 0
        if(activeProcessOpt.isEmpty()){
            activeProcessOpt = processStackRepository.findFirstByActive((byte) 0);
            // se il processo active = 0 esiste, imposta active = 1 e dateStart a newDate()
            if(activeProcessOpt.isPresent()){
                activeProcess = activeProcessOpt.get();
                activeProcess.setActive((byte) 1);
                activeProcess.setDateStart(new Date());
                processStackRepository.save(activeProcess);
            }
        } else {
            activeProcess = activeProcessOpt.get();
        }

        // se non esistono processi (non ci sono active = 0) non eseguo la chiamata ed esco

        // a questo punto avvio la chiamata verso MS2
        if(activeProcess!=null){
            response = ms2Client.jobTestClient(activeProcess.getProcessId());
            // se la chiamata ha successo, setto active = 0 e dateEnd a newDate()
            if(response != null){
                if(response.isStatus()){
                    activeProcess.setDateEnd(new Date());
                }
                JobTest jobTest;
                Optional<JobTest> jobTestOpt = jobTestRepository.findByProcessId(activeProcess.getProcessId());
                if(jobTestOpt.isEmpty()){
                    jobTest = new JobTest();
                    jobTest.setProcessId(activeProcess.getProcessId());
                    jobTestRepository.save(jobTest);
                } else {
                    jobTest=jobTestOpt.get();
                }
                jobTest.setResponseMessage(response.getResponseMessage());
                jobTest.setResponseDateTime(response.getResponseDateTime());
                jobTest.setAttempt( (short) (jobTest.getAttempt()+1) );
                jobTestRepository.save(jobTest);
                processStackRepository.save(activeProcess);
            }
        }

    }
}
