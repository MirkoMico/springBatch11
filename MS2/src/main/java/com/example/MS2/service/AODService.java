package com.example.MS2.service;


import com.example.MS2.dto.InfortunioDTO;

import java.util.List;

public interface AODService {

    InfortunioDTO getInfortuni(String regione, int annoAccadimento, int meseAccadimento);

}



