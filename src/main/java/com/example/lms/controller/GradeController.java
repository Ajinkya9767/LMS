package com.example.lms.controller;

import com.example.lms.entity.Grade;
import com.example.lms.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.createGrade(grade));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGrade(@PathVariable Long id) {
        return gradeService.getGradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<Grade> getGradeBySubmission(@PathVariable Long submissionId) {
        return gradeService.getGradeBySubmissionId(submissionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}