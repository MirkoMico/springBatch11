package com.example.MS1.service;


import com.example.MS1.dto.M1Dto;
import com.example.MS1.model.M1;
import com.example.MS1.repository.M1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class M1ServiceImpl implements M1Service{

    @Autowired
    M1Repository m1Repository;

    @Override
    public void saveBaw1(M1Dto m1Dto) {
        M1 m1 = new M1(m1Dto);
        m1Repository.save(m1);
    }

    @Override
    public void saveBaw2(M1Dto m1Dto) {
        M1 m1 = new M1(m1Dto);
    }

    @Override
    public List<M1Dto> getBaw1() {
        List<M1> m1List = m1Repository.findAll();
        List<M1Dto> m1DtoList = new ArrayList<>();
        for(M1 mario:m1List){
            m1DtoList.add(new M1Dto(mario));
        }
        return m1DtoList;
    }

    @Override
    public List<M1Dto> getBaw2() {
        List<M1> m2List = m1Repository.findAll();
        List<M1Dto> m2DtoList = new ArrayList<>();
        for(M1 mario:m2List){
            m2DtoList.add(new M1Dto(mario));
        }
        return m2DtoList;
    }

}
