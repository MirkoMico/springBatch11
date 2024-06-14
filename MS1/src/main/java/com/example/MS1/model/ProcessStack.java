package com.example.MS1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "process_stack")
@AllArgsConstructor
@NoArgsConstructor
public class ProcessStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String processId;
   // private String tipo;
   // private String frequenza;
    private Date date;
    private Boolean inviato;
    private Boolean concluso;
    private Date dateEnd;



}
