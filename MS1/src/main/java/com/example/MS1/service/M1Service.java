package com.example.MS1.service;

import com.example.MS1.dto.M1Dto;

import java.util.List;

public interface M1Service{

    public void saveBaw1(M1Dto m1Dto);
    public void saveBaw2(M1Dto m1Dto);

    public List<M1Dto> getBaw1();
    public List<M1Dto> getBaw2();

}
