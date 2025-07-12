package com.example.lms.service;

import com.example.lms.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    Grade createGrade(Grade grade);

    Optional<Grade> getGradeById(Long id);

    Optional<Grade> getGradeBySubmissionId(Long submissionId);

    List<Grade> getAllGrades();

    Grade updateGrade(Long id, Grade grade);

    void deleteGrade(Long id);
}