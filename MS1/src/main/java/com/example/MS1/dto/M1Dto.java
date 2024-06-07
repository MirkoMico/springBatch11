package com.example.MS1.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class M1Dto {

    private String object;
    private LocalDateTime date;

}
