package com.example.MS1.controller;

import com.example.MS1.dto.M1Dto;
import com.example.MS1.service.M1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baw")
public class BawController {

    @Autowired
    M1Service m1Service;

    @GetMapping("/baw1")
    public List<M1Dto> getBaw1(){
        return m1Service.getBaw();
    }
    @GetMapping("/baw2")
    public List<M1Dto> getBaw2(){
        return m1Service.getBaw();
    }
    @GetMapping("/baw3")
    public String getBaw3(){
        return "baw3";
    }
    @GetMapping("/baw4")
    public String get4Baw4(){
        return "baw4";
    }

    @PostMapping("/baw1")
    public ResponseEntity<M1Dto> postBaw1(@RequestBody M1Dto baw1){
        m1Service.saveBaw(baw1);
        return ResponseEntity.ok().body(baw1);
    }

    @PostMapping("/baw2")
    public ResponseEntity<M1Dto> postBaw2(@RequestBody M1Dto baw2){
        m1Service.saveBaw(baw2);
        return ResponseEntity.ok().body(baw2);
    }
}
