package com.example.CRS.controller;

import com.example.CRS.repository.AdminRepository;
import com.example.CRS.repository.StudentRepository;
import com.example.CRS.repository.UserRepository;
import com.example.CRS.service.AuthRequest;
import com.example.CRS.service.AuthResponse;
import com.example.CRS.service.JwtUtil;
import lombok.RequiredArgsConstructor;
import com.example.CRS.model.Admin;
import com.example.CRS.model.Student;
import com.example.CRS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Component
public class AuthController {

@Autowired
    private  AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private  StudentRepository studentRepo;
    @Autowired
    private  AdminRepository adminRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register-student")
    public String registerStudent(@RequestBody Student student) {
        student.setPassword(encoder.encode(student.getPassword()));
        student.setRole("STUDENT");
        studentRepo.save(student);
        return "Student registered successfully!";
    }

    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        admin.setRole("ADMIN");
        adminRepo.save(admin);
        return "Admin registered successfully!";
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
            User user = userRepo.findByUsername(req.getUsername())
                    .orElseThrow(() -> new RuntimeException("❌ User not found"));

            System.out.println("🧠 Login attempt for username: " + req.getUsername());
            System.out.println("🔑 Raw password from request: " + req.getPassword());
            System.out.println("🔒 Encoded password from DB: " + user.getPassword());

            if (!encoder.matches(req.getPassword(), user.getPassword())) {
                System.out.println("❌ Password mismatch!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Password mismatch!");
            }

            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            System.out.println("✅ Login success, JWT generated");

            return ResponseEntity.ok(new AuthResponse(token, user.getRole()));

        } catch (Exception e) {
            System.out.println("❌ Exception during login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Invalid login");
        }
    }



}

