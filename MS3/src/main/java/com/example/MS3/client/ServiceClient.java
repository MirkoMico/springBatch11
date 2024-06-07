package com.example.MS3.client;

import com.example.MS3.dto.M1Dto;
import com.example.MS3.dto.InfortunioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ServiceClient {

    @Autowired
    private RestTemplate restTemplate;


   // public String getEndpoint() {
     //   return restTemplate.postForObject("http://localhost:8080/baw/baw1", String.class);
   // }


    public List<M1Dto> getBaw1(){
        return restTemplate.getForObject("http://MS1/baw/baw1", List.class);
    }


    public ResponseEntity<InfortunioDTO> getInfortuniFromMicroservice(String regione, int annoAccadimento, int meseAccadimento) {
        String url = "http://MS2/infortuni?Regione=" + regione + "&AnnoAccadimento=" + annoAccadimento + "&MeseAccadimento=" + meseAccadimento;

        ResponseEntity<InfortunioDTO> response = restTemplate.getForEntity(url, InfortunioDTO.class);
      //  System.out.println("risposta dal microservizio: " + response.getBody());

        return response;
    }

    //mi fai il il metodo per prendere la chiamata post /baw/baw1


}
