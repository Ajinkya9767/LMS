package com.example.lms.controller;

import com.example.lms.entity.Submission;
import com.example.lms.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> createSubmission(@RequestBody Submission submission) {
        return ResponseEntity.ok(submissionService.createSubmission(submission));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmission(@PathVariable Long id) {
        return submissionService.getSubmissionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Submission> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        return submissionService.getSubmissionsByAssignment(assignmentId);
    }

    @GetMapping("/student/{studentId}")
    public List<Submission> getSubmissionsByStudent(@PathVariable Long studentId) {
        return submissionService.getSubmissionsByStudent(studentId);
    }

    @GetMapping("/assignment/{assignmentId}/student/{studentId}")
    public List<Submission> getSubmissionsByAssignmentAndStudent(@PathVariable Long assignmentId, @PathVariable Long studentId) {
        return submissionService.getSubmissionsByAssignmentAndStudent(assignmentId, studentId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.noContent().build();
    }
}