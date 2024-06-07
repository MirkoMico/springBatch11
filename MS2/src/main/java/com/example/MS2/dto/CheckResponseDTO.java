package com.example.MS2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor

public class CheckResponseDTO {

    private String responseMessage;
    private String processId;
    private Date responseDateTime;
    private boolean status;

    public CheckResponseDTO() {
        this.status = false;
    }
}
