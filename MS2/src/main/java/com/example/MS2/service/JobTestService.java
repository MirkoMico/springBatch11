package com.example.MS2.service;

import com.example.MS2.dto.CheckRequestDTO;
import com.example.MS2.dto.CheckResponseDTO;

public interface JobTestService {
    public CheckResponseDTO check(CheckRequestDTO request);
}
