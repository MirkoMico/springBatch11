package com.example.MS1.controller;

import com.example.MS1.dto.ProcessStackDto;
import com.example.MS1.model.ProcessStack;
import com.example.MS1.service.M1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/baw")
public class BawController {

    @Autowired
    M1Service m1Service;
    @PostMapping("/process")
    public ResponseEntity<ProcessStackDto> saveProcess(@RequestBody ProcessStackDto processStackDto){
        try {
        m1Service.saveProcess(processStackDto);
        return ResponseEntity.ok().body(processStackDto);

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/get-process")
    public Optional<List<ProcessStack>> getprocess(){

        return m1Service.getAllProcess();
    }

    @PostMapping("/notifyProcessTermination")
    public void notifyProcessTermination(@RequestParam("processId") String processId) {
        // Gestisci la terminazione del processo qui
        System.out.println("Process with ID " + processId + " has terminated.");

        m1Service.handleProcessTermination(processId);
    }



  /*  @GetMapping("/baw2")
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
    }*/
}
