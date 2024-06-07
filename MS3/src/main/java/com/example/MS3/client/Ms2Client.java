package com.example.MS3.client;

import com.example.MS3.dto.CheckRequestDTO;
import com.example.MS3.dto.CheckResponseDTO;
import com.example.MS3.dto.InfortunioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class Ms2Client {

    @Autowired
    private RestTemplate restTemplate;

    public CheckResponseDTO jobTestClient(String processId) throws Exception {
        CheckRequestDTO request = new CheckRequestDTO();
        request.setProcessId(processId);
        String url = "http://MS2/api/job-test/check";


        ResponseEntity<CheckResponseDTO> response = restTemplate.postForEntity(url, request, CheckResponseDTO.class);
        //  System.out.println("risposta dal microservizio: " + response.getBody());
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("errore di connessione");
        }

        return response.getBody();

    }
}
