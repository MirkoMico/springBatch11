package com.example.MS3.repository;

import com.example.MS3.entity.ProcessStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ProcessStackRepository extends JpaRepository<ProcessStack, Integer> {
    public Optional<ProcessStack> findFirstByActiveAndDateEnd (byte active, Date dateEnd);
    public Optional<ProcessStack> findFirstByActive(byte active);
}
