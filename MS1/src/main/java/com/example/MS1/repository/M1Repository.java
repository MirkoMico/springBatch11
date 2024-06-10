package com.example.MS1.repository;

import com.example.MS1.model.ProcessStack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface M1Repository extends JpaRepository<ProcessStack, Long> {

   List< ProcessStack> findByInviatoFalse();
}
