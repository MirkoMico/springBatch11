package com.example.MS1.controller;


import com.example.MS1.service.ClouderaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClouderaController {
    @Autowired
    private ClouderaService clouderaService;



    @PostMapping("/infortunio/mensile")
    public String getClouder1() {
        String tipo="infortunio";
        String frequenza="mensile";

        clouderaService.salvaDati(tipo,frequenza);

        return "salvato: infortunio mensile";
    }

    @GetMapping("/infortunio/semestrale")
    public String getClouder2() {
        return "infortunio semestrale";
    }

    @GetMapping("/malattia/mensile")
    public String getClouder3() {
        return "malattia mensile";
    }

    @GetMapping("/malattia/semestrale")
    public String getClouder4() {
        return "malattia semestrale";
    }




}
