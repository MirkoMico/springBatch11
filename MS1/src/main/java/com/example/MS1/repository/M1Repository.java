package com.example.MS1.repository;

import com.example.MS1.model.M1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface M1Repository extends JpaRepository<M1, Long> {
}
