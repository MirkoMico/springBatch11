package com.example.MS1.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class M1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String processId;
    private Date date;

}
