package com.example.MS1.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProcessStackDto {

    private String processId;
   // private String tipo;
   // private String frequenza;
    private Date date;
    private Boolean inviato;
    private Boolean concluso;
    private Date dateEnd;

}
