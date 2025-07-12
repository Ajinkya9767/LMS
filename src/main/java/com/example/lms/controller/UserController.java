package com.example.lms.controller;

import com.example.lms.entity.User;
import com.example.lms.model.UserRequestDTO;
import com.example.lms.model.UserResponseDTO;
import com.example.lms.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    // Create new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequest) {
        logger.info("Received request to create user: {}", userRequest);
        User createdUser = userService.createUser(userRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Update user details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequest) {
        User updatedUser = userService.updateUser(id, userRequest);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

