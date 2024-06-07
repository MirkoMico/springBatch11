package com.example.MS2.repository;

import com.example.MS2.entity.JobTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobTestRepository extends JpaRepository<JobTest, Long> {

    public Optional<JobTest> findByProcessId(String processId);
}
