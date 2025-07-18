package com.example.CRS.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
    @Inheritance(strategy = InheritanceType.JOINED)
    @Data
    public  class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;



        private String username;
        private String password;
        private String role; // STUDENT or ADMIN

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


