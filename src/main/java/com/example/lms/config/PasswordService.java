package com.example.lms.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService() {
        // Initialize the BCryptPasswordEncoder instance
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Method to hash a password
    public String hashPassword(String password) {
        return passwordEncoder.encode(password); // This generates the hash
    }

    // Method to verify a raw password against the hash stored in the database
    public boolean verifyPassword(String rawPassword, String storedPasswordHash) {
        return passwordEncoder.matches(rawPassword, storedPasswordHash);
    }
}

