package com.example.lms.repository;

import com.example.lms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUserId(Long userId);

    List<Enrollment> findByCourseId(Long courseId);
}