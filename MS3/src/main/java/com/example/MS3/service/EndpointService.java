package com.example.MS3.service;

import com.example.MS3.client.ServiceClient;
import com.example.MS3.dto.M1DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EndpointService {

    @Autowired
    private ServiceClient serviceClient;
    public List<M1DTO> getBaw1() {
        return serviceClient.getBaw1();
    }
}
