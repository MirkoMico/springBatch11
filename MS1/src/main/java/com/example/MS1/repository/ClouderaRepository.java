package com.example.MS1.repository;

import com.example.MS1.model.Cloudera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClouderaRepository extends JpaRepository<Cloudera, Long> {

}
