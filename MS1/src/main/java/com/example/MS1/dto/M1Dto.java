package com.example.MS1.dto;

import com.example.MS1.model.M1;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
//@Builder
public class M1Dto {

    private String object;
    private LocalDateTime date;

    public M1Dto() {
    }

    public M1Dto(M1 m1) {
        this.object = m1.getObject();
        this.date = m1.getDate();
    }

    public String getObject() {
        return object;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
