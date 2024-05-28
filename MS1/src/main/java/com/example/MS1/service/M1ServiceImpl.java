package com.example.MS1.service;


import com.example.MS1.dto.M1Dto;
import com.example.MS1.model.M1;
import com.example.MS1.repository.M1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class M1ServiceImpl implements M1Service {

    @Autowired
    M1Repository m1Repository;

    @Override
    public void saveBaw(M1Dto m1Dto) {
        M1 m1 = M1.builder()
                .object(m1Dto.getObject())
                .date(m1Dto.getDate())
                .build();
        m1Repository.save(m1);
    }

    @Override
    public List<M1Dto> getBaw() {
        List<M1> m1List = m1Repository.findAll();
        return m1List.stream().map(m1 -> M1Dto.builder()
                .object(m1.getObject())
                .date(m1.getDate())
                .build()).collect(Collectors.toList());
    }

}
