package com.example.CRS.service;


import com.example.CRS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.CRS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private  UserRepository repo;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // ✅ Debug prints to verify login flow
        System.out.println("🔐 Logging in user: " + user.getUsername());
        System.out.println("🔑 Encoded password in DB: " + user.getPassword());
        System.out.println("🛡️ Role from DB: " + user.getRole());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().toUpperCase()) // Automatically becomes ROLE_STUDENT or ROLE_ADMIN
                .build();
    }

}

