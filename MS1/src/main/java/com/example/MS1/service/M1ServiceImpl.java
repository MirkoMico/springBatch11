package com.example.MS1.service;

import com.example.MS1.dto.ProcessStackDto;
import com.example.MS1.model.ProcessStack;
import com.example.MS1.repository.M1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class M1ServiceImpl implements M1Service {

    @Autowired
    M1Repository m1Repository;


    @Override
    public void saveProcess(ProcessStackDto processStackDto) {

        ProcessStack processStack = ProcessStack.builder()
                .processId(processStackDto.getProcessId())
                .date(new Date())
                .inviato(false)
                .build();
        m1Repository.save(processStack);

    }

   /* @Override
    public void saveBaw(M1Dto m1Dto) {
        M1 m1 = M1.builder()
                .object(m1Dto.getObject())
                .date(LocalDateTime.now())
                .build();
        m1Repository.save(m1);
    }*/



    @Override
    public List<ProcessStackDto> getProcess() {
        List<ProcessStack> processStackList = m1Repository.findAll();
        return processStackList.stream().map(processStack -> ProcessStackDto.builder()
                .processId(processStack.getProcessId())
                .date(new Date())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Optional<List<ProcessStack>> getAllProcess() {
        List<ProcessStack> entities = m1Repository.findByInviatoFalse();
        if (!entities.isEmpty()) {
            for (ProcessStack entity : entities) {
                entity.setInviato(true);
            }
            m1Repository.saveAll(entities);
            return Optional.of(entities);
        } else {
            return Optional.empty();
        }
    }



}
