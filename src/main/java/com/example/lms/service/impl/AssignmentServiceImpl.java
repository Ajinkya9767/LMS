package com.example.lms.service.impl;

import com.example.lms.entity.Assignment;
import com.example.lms.repository.AssignmentRepository;
import com.example.lms.service.AssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public List<Assignment> getAssignmentsByCourseId(Long courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    @Override
    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Assignment existing = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        existing.setTitle(updatedAssignment.getTitle());
        existing.setDescription(updatedAssignment.getDescription());
        existing.setCourseId(updatedAssignment.getCourseId());
        existing.setDueDate(updatedAssignment.getDueDate());

        return assignmentRepository.save(existing);
    }

    @Override
    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
}