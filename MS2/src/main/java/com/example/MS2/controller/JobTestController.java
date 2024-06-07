package com.example.MS2.controller;

import com.example.MS2.dto.CheckRequestDTO;
import com.example.MS2.dto.CheckResponseDTO;
import com.example.MS2.service.JobTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/job-test")
public class JobTestController {
    @Autowired
    JobTestService jobTestService;

    @PostMapping("/check")
    public CheckResponseDTO check(@RequestBody CheckRequestDTO request){
        return jobTestService.check(request);
    }
}
