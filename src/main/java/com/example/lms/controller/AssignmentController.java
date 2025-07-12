package com.example.lms.controller;

import com.example.lms.entity.Assignment;
import com.example.lms.service.AssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        return ResponseEntity.ok(assignmentService.createAssignment(assignment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/course/{courseId}")
    public List<Assignment> getAssignmentsByCourse(@PathVariable Long courseId) {
        return assignmentService.getAssignmentsByCourseId(courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, assignment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}