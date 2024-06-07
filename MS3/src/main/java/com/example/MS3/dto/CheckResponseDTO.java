package com.example.MS3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckResponseDTO {

    private String responseMessage;
    private String processId;
    private Date responseDateTime;
    private boolean status;
}
