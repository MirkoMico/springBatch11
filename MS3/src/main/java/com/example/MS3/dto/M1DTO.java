package com.example.MS3.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class M1DTO {

    private String object;
    private LocalDateTime date;

}
