package com.example.MS1.service;


import com.example.MS1.dto.M1Dto;
import com.example.MS1.model.M1;
import com.example.MS1.repository.M1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class M1ServiceImpl implements M1Service {

    @Autowired
    M1Repository m1Repository;


    @Override

    public void saveProcess(M1Dto m1Dto) {
        M1 m1 = M1.builder()
                .processId(m1Dto.getProcessId())
                .date(new Date())
                .build();
        m1Repository.save(m1);
    }

    @Override
    public List<M1Dto> getProcess() {
        List<M1> m1List = m1Repository.findAll();
        return m1List.stream().map(m1 -> M1Dto.builder()
                .processId(m1.getProcessId())
                .date(new Date())
                .build()).collect(Collectors.toList());
    }



}
