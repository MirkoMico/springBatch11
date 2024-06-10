package com.example.MS1.service;

import com.example.MS1.dto.ProcessStackDto;
import com.example.MS1.model.ProcessStack;

import java.util.List;
import java.util.Optional;

public interface M1Service{

    public void saveProcess(ProcessStackDto processStackDto);

    public List<ProcessStackDto> getProcess();

    public Optional<List<ProcessStack>> getAllProcess();

}
