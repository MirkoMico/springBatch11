package com.example.MS1.controller;


import com.example.MS1.service.M1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cloudera")
public class ClouderaController {

    @Autowired
    M1Service m1Service;

    @GetMapping("/cloudera1")
    public String getClouder1() {
        return "Cloudera1";
    }

    @GetMapping("/cloudera2")
    public String getClouder2() {
        return "Cloudera2";
    }

    @GetMapping("/cloudera3")
    public String getClouder3() {
        return "Cloudera3";
    }

    @GetMapping("/cloudera4")
    public String getClouder4() {
        return "Cloudera4";
    }
}
