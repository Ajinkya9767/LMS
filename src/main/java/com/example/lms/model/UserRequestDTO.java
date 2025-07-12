package com.example.lms.model;

import com.example.lms.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
