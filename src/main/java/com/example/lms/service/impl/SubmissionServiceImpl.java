package com.example.lms.service.impl;

import com.example.lms.entity.Submission;
import com.example.lms.repository.SubmissionRepository;
import com.example.lms.service.SubmissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public Submission createSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    @Override
    public List<Submission> getSubmissionsByStudent(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    @Override
    public List<Submission> getSubmissionsByAssignmentAndStudent(Long assignmentId, Long studentId) {
        return submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId);
    }

    @Override
    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }
}