package com.example.MS3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndpointDTO {
    private String endpointPath;
    private String endpointType;
    private LocalDateTime endpointDate;
}
