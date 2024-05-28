package com.example.MS1.dto;

import com.example.MS1.model.M1;
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
