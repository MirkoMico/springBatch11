package com.example.MS2.controller;

import com.example.MS2.dto.InfortunioDTO;
import com.example.MS2.service.AODService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AODController {

    @Autowired
    AODService aodService;

    @GetMapping("/infortuni")
    public ResponseEntity<InfortunioDTO> getInfortuni(
            @RequestParam String Regione,
            @RequestParam int AnnoAccadimento,
            @RequestParam int MeseAccadimento) {

        InfortunioDTO response = aodService.getInfortuni(Regione, AnnoAccadimento, MeseAccadimento);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
