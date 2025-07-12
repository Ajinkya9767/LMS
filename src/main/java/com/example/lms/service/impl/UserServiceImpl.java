package com.example.lms.service.impl;

import com.example.lms.config.PasswordService;
import com.example.lms.entity.Role;
import com.example.lms.model.UserRequestDTO;
import com.example.lms.model.UserResponseDTO;
import com.example.lms.repository.RoleRepository;
import com.example.lms.repository.UserRepository;
import com.example.lms.entity.User;
import com.example.lms.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableAutoConfiguration
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return convertListToDTO(userList);
    }

    private List<UserResponseDTO> convertListToDTO(List<User> userList) {
        List<UserResponseDTO> userResponseDTOList =  new ArrayList<>();
        for (User user : userList) {
            UserResponseDTO userResponse = new UserResponseDTO();
            userResponse.setUserId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setUsername(user.getUsername());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            Role role = user.getRole();
            Role roleResponse = new Role();
            roleResponse.setId(role.getId());
            roleResponse.setName(role.getName());
            roleResponse.setDescription(role.getDescription());
            userResponse.setRole(roleResponse);
            userResponseDTOList.add(userResponse);
        }
        return userResponseDTOList;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserResponseDTO userResponse = new UserResponseDTO();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userResponse = convertToDTO(user);
        }

        return userResponse;
    }

    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setUserId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        Role role = user.getRole();
        Role roleResponse = new Role();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        roleResponse.setDescription(role.getDescription());
        userResponse.setRole(roleResponse);

        return userResponse;
    }

    @Transactional
    @Override
    public User createUser(UserRequestDTO userRequest) {
        Role role = userRequest.getRole();
        if (role != null && role.getId() == null) {
            // If the role is new (id is null), save it
            role = roleRepository.save(role);
        }

        User user = new User();
        if (!ObjectUtils.isEmpty(userRequest.getId())) {
            user.setId(userRequest.getId());
            user.setUpdatedAt(LocalDateTime.now());
        } else {
            user.setCreatedAt(LocalDateTime.now());
        }
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordService.hashPassword(userRequest.getPassword()));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserRequestDTO userRequest) {
        if (userRepository.existsById(id)) {
            userRequest.setId(id);
            return createUser(userRequest);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
