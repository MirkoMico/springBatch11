package com.example.MS3.service.impl;

import com.example.MS3.client.Ms1Client;
import com.example.MS3.client.Ms2Client;
import com.example.MS3.dto.CheckRequestDTO;
import com.example.MS3.dto.CheckResponseDTO;
import com.example.MS3.entity.JobTest;
import com.example.MS3.entity.ProcessStack;
import com.example.MS3.repository.JobTestRepository;
import com.example.MS3.repository.ProcessStackRepository;
import com.example.MS3.service.JobTestService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.springframework.boot.cloud.CloudPlatform.getActive;

@Service

public class JobTestServiceImpl implements JobTestService {

    @Autowired
    private Ms2Client ms2Client;
    @Autowired
    private Ms1Client ms1Client;

    @Autowired
    private ProcessStackRepository processStackRepository;
    @Autowired
    private JobTestRepository jobTestRepository;


    @Override
    public void check() throws Exception {

        //avvia la chiamata verso MS1
        Optional<List<ProcessStack>> stakMs1 = ms1Client.getEntitiesFromM1Service();
        if (stakMs1.isPresent()) {
            for (ProcessStack processStackMs3 : stakMs1.get()) {
                ProcessStack processStack = new ProcessStack();
                processStack.setActive((byte) 0);
                processStack.setDateStart(null);
                processStack.setDateEnd(null);
                processStack.setProcessId(processStackMs3.getProcessId());
                processStackRepository.save(processStack);

            }
        }


        // prelevo il processo attivo(active=1 and dateEnd = null)
        Optional<ProcessStack> activeProcessOpt = processStackRepository.findFirstByActiveAndDateEnd((byte) 1, null);


        CheckResponseDTO response = new CheckResponseDTO();
        ProcessStack activeProcess = null;
        // se il processo non esiste seleziono la prima riga con active=0
        if (activeProcessOpt.isEmpty()) {
            activeProcessOpt = processStackRepository.findFirstByActive((byte) 0);
            //se il processo active=0 esiste, imposto active a 1 e data start a new Date
            if (activeProcessOpt.isPresent()) {
                activeProcess = activeProcessOpt.get();
                activeProcess.setActive((byte) 1);
                activeProcess.setDateStart(new Date());
                processStackRepository.save(activeProcess);
            }
        } else {
            activeProcess = activeProcessOpt.get();
        }
        //se non esistono processi (non ci sono active =0) non eseguo la chiamata ed esco


        //avvio la chiamata verso Ms2
        if (activeProcess != null) {
            response = ms2Client.jobTestClient(activeProcess.getProcessId());
            //se la chiamata ha successo, imposto active a 0 e dataEnd a new Date
            if (response != null) {
                if (response.isStatus()) {
                    activeProcess.setDateEnd(new Date());
                }
                JobTest jobTest;
                Optional<JobTest> jobTestOpt = jobTestRepository.findByProcessId(activeProcess.getProcessId());
                if (jobTestOpt.isEmpty()) {
                    jobTest = new JobTest();
                    jobTest.setProcessId(activeProcess.getProcessId());
                    jobTestRepository.save(jobTest);
                } else {
                    jobTest = jobTestOpt.get();
                }
                jobTest.setResponseMessage(response.getResponseMessage());
                jobTest.setResponseDateTime(response.getResponseDateTime());
                jobTest.setAttempt((short) (jobTest.getAttempt() + 1));
                jobTestRepository.save(jobTest);
                processStackRepository.save(activeProcess);
            }
        }


        // Notifica a MS1 che il processo è terminato
        if (activeProcess != null && activeProcess.getDateEnd() != null) {
            ms1Client.notifyProcessTermination(activeProcess.getProcessId());

        }


    }

    public void check1() {
        System.out.println("check1");
        //avvia la chiamata verso MS1
        Optional<List<ProcessStack>> stakMs1 = ms1Client.getEntitiesFromM1Service();
        if (stakMs1.isPresent()) {
            for (ProcessStack processStackMs3 : stakMs1.get()) {
                ProcessStack processStack = new ProcessStack();
                processStack.setActive((byte) 0);
                processStack.setDateStart(null);
                processStack.setDateEnd(null);
                processStack.setProcessId(processStackMs3.getProcessId());
                processStackRepository.save(processStack);

            }
        }
    }

    public void check2() throws Exception {
        System.out.println("check2");

        // prelevo il processo attivo(active=1 and dateEnd = null)
        Optional<ProcessStack> activeProcessOpt = processStackRepository.findFirstByActiveAndDateEnd((byte) 1, null);


        CheckResponseDTO response = new CheckResponseDTO();
        ProcessStack activeProcess = null;
        // se il processo non esiste seleziono la prima riga con active=0
        if (activeProcessOpt.isEmpty()) {
            activeProcessOpt = processStackRepository.findFirstByActive((byte) 0);
            //se il processo active=0 esiste, imposto active a 1 e data start a new Date
            if (activeProcessOpt.isPresent()) {
                activeProcess = activeProcessOpt.get();
                activeProcess.setActive((byte) 1);
                activeProcess.setDateStart(new Date());
                // activeProcess.setInviato((byte) 0);
                processStackRepository.save(activeProcess);
            }
        } else {
            activeProcess = activeProcessOpt.get();
        }
        //se non esistono processi (non ci sono active =0) non eseguo la chiamata ed esco


    }

    public void check3() throws Exception {
        System.out.println("check3");
        //avvio la chiamata verso Ms2
        Optional<ProcessStack> activeProcessOpt = processStackRepository.findFirstByActiveAndDateEnd((byte) 1, null);
        if (activeProcessOpt.isPresent()) {
            ProcessStack activeProcess = activeProcessOpt.get();
            CheckResponseDTO response = ms2Client.jobTestClient(activeProcess.getProcessId());
            if (response != null) {
                if (response.isStatus()) {
                    activeProcess.setDateEnd(new Date());
                }
                JobTest jobTest;
                Optional<JobTest> jobTestOpt = jobTestRepository.findByProcessId(activeProcess.getProcessId());
                if (jobTestOpt.isEmpty()) {
                    jobTest = new JobTest();
                    jobTest.setProcessId(activeProcess.getProcessId());

                    jobTestRepository.save(jobTest);
                } else {
                    jobTest = jobTestOpt.get();
                }
                jobTest.setResponseMessage(response.getResponseMessage());
                jobTest.setResponseDateTime(response.getResponseDateTime());
                jobTest.setAttempt((short) (jobTest.getAttempt() + 1));
                jobTestRepository.save(jobTest);
                processStackRepository.save(activeProcess);
            }
            // Notifica a MS1 che il processo è terminato
           // ms1Client.notifyProcessTermination(activeProcess.getProcessId());
            if (activeProcess != null && activeProcess.getDateEnd() != null) {
                ms1Client.notifyProcessTermination(activeProcess.getProcessId());

            }

        }


    }


}