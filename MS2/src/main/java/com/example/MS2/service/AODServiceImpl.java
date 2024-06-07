package com.example.MS2.service;

import com.example.MS2.dto.InfortunioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AODServiceImpl implements AODService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public InfortunioDTO getInfortuni(String regione, int annoAccadimento, int meseAccadimento) {
        String url = String.format("https://dati.inail.it/api/OpenData/DatiConCadenzaSemestraleInfortuni?Regione=%s&AnnoAccadimento=%d&MeseAccadimento=%02d",
                regione, annoAccadimento, meseAccadimento);

        try {
            return restTemplate.getForObject(url, InfortunioDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
