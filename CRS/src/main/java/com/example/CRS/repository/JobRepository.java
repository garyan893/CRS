package com.example.CRS.repository;



import com.example.CRS.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface JobRepository extends JpaRepository<Job, Long> {
}

