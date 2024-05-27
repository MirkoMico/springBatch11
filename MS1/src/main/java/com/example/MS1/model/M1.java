package com.example.MS1.model;


import com.example.MS1.dto.M1Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
//@Builder
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class M1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String object;
    private LocalDateTime date;

    public M1(Long id, String object, LocalDateTime date) {
        this.id = id;
        this.object = object;
        this.date = date;
    }

    public M1() {
    }

    public M1(M1Dto m1Dto) {
        this.object = m1Dto.getObject();
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
