package com.example.lms.service;

import com.example.lms.entity.User;
import com.example.lms.model.UserRequestDTO;
import com.example.lms.model.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    User createUser(UserRequestDTO user);

    User updateUser(Long id, UserRequestDTO user);

    boolean deleteUser(Long id);
}

