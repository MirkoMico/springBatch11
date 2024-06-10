package com.example.MS1.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@Table(name = "process_stack")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String processId;
    private Date date;
    private Boolean inviato;
}
