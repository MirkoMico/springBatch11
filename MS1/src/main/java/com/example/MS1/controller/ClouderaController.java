package com.example.MS1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ClouderaController {



    @GetMapping("/infortunio/mensile")
    public String getClouder1() {
        return "infortunio mensile";
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
