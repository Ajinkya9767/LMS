package com.example.lms.service;

import com.example.lms.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    Enrollment saveEnrollment(Enrollment enrollment);

    Optional<Enrollment> getEnrollmentById(Long id);

    List<Enrollment> getAllEnrollments();

    void deleteEnrollment(Long id);

    Enrollment updateEnrollment(Long id, Enrollment enrollment);
}