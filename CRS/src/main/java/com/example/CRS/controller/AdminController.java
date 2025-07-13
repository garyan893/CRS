package com.example.CRS.controller;



import com.example.CRS.model.Job;
import com.example.CRS.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private JobRepository jobRepo;

    // POST /api/admin/jobs — create new job
    @PostMapping("/jobs")
    public ResponseEntity<?> postJob(@RequestBody Job job) {
        // 🔍 Debug: print incoming values
        System.out.println("📥 Received Job:");
        System.out.println("Company Name: " + job.getCompanyName());
        System.out.println("Eligibility: " + job.getEligibility());
        System.out.println("Deadline: " + job.getDeadline());

        jobRepo.save(job);
        return ResponseEntity.ok("✅ Job posted successfully");
    }

    // GET /api/admin/jobs — fetch all posted jobs
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobs() {
        List<Job> jobs = jobRepo.findAll();
        return ResponseEntity.ok(jobs);
    }
}

