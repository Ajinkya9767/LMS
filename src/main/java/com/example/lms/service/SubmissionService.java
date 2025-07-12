package com.example.lms.service;

import com.example.lms.entity.Submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionService {
    Submission createSubmission(Submission submission);

    Optional<Submission> getSubmissionById(Long id);

    List<Submission> getAllSubmissions();

    List<Submission> getSubmissionsByAssignment(Long assignmentId);

    List<Submission> getSubmissionsByStudent(Long studentId);

    List<Submission> getSubmissionsByAssignmentAndStudent(Long assignmentId, Long studentId);

    void deleteSubmission(Long id);
}
