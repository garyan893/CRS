package com.example.CRS.service;




public class AuthResponse {

    private String token;
    private String role;

    // ✅ Required no-arg constructor (needed by Jackson)
    public AuthResponse() {
    }

    // ✅ Custom constructor that matches the controller call
    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    // ✅ Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


