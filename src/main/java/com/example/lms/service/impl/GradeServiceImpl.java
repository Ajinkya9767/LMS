package com.example.lms.service.impl;

import com.example.lms.entity.Grade;
import com.example.lms.repository.GradeRepository;
import com.example.lms.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Optional<Grade> getGradeBySubmissionId(Long submissionId) {
        return gradeRepository.findBySubmissionId(submissionId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade updateGrade(Long id, Grade updatedGrade) {
        Grade existing = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        existing.setGrade(updatedGrade.getGrade());
        existing.setFeedback(updatedGrade.getFeedback());

        return gradeRepository.save(existing);
    }

    @Override
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}