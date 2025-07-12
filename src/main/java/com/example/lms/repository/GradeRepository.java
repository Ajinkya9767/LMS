package com.example.lms.repository;

import com.example.lms.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findBySubmissionId(Long submissionId);
}