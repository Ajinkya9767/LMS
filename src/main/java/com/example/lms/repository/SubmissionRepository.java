package com.example.lms.repository;

import com.example.lms.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignmentId(Long assignmentId);

    List<Submission> findByStudentId(Long studentId);

    List<Submission> findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}