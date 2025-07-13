package com.example.CRS.repository;


import com.example.CRS.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
