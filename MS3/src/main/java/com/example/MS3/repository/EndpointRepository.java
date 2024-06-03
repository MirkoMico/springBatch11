package com.example.MS3.repository;

import com.example.MS3.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Serializable> {
}
