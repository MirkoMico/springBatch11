package com.example.MS2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobTestDTO {

    private String responseMessage;
    private LocalDateTime responseDateTime;
}
