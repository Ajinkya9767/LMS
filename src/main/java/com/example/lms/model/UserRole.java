package com.example.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    TEACHER(1, "Teacher"), INSTRUCTOR(2, "Instructor"), ADMIN(3, "Admin"), STUDENT(4, "Student");

    private final int value;
    private final String name;
}
