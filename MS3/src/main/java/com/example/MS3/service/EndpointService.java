package com.example.MS3.service;

import com.example.MS3.client.ServiceClient;
import com.example.MS3.dto.M1Dto;
import com.example.MS3.dto.InfortunioDTO;
import com.example.MS3.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointService {

    @Autowired
    private EndpointRepository endpointRepository;
    @Autowired
    private ServiceClient serviceClient;


    public List<M1Dto> getBaw1(){
        return serviceClient.getBaw1();
    }

    public ResponseEntity<InfortunioDTO> getInfortuni(String regione, int annoAccadimento, int meseAccadimento) {


        // Passo la stringa processata al microservizio tramite il ServiceClient
        return serviceClient.getInfortuniFromMicroservice(regione, annoAccadimento, meseAccadimento);
    }
}
