package com.example.MS1.service.impl;

import com.example.MS1.model.Cloudera;
import com.example.MS1.repository.ClouderaRepository;
import com.example.MS1.service.ClouderaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service

public class ClouderaServiceImpl implements ClouderaService {

    @Autowired
    private ClouderaRepository clouderaRepository;
    @Override
    public void salvaDati(String tipo, String frequenza) {
        String codice = generaCodice(tipo, frequenza);
        Cloudera cloudera = new Cloudera();
        cloudera.setTipo(tipo);
        cloudera.setFrequenza(frequenza);
        cloudera.setCodice(codice);
        clouderaRepository.save(cloudera);
        
      

    }

    private String generaCodice(String tipo, String frequenza) {
        String input = tipo + frequenza;
        return UUID.nameUUIDFromBytes(input.getBytes(StandardCharsets.UTF_8)).toString();
    }
}
