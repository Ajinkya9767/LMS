package com.example.lms.service.impl;

import com.example.lms.entity.Enrollment;
import com.example.lms.repository.EnrollmentRepository;
import com.example.lms.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollmentDetails) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setUserId(enrollmentDetails.getUserId());
        enrollment.setCourseId(enrollmentDetails.getCourseId());
        enrollment.setEnrollmentStatus(enrollmentDetails.getEnrollmentStatus());
        enrollment.setGrade(enrollmentDetails.getGrade());

        return enrollmentRepository.save(enrollment);
    }
}
