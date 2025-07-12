package com.example.lms.service;

import com.example.lms.entity.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    Assignment createAssignment(Assignment assignment);

    Optional<Assignment> getAssignmentById(Long id);

    List<Assignment> getAllAssignments();

    List<Assignment> getAssignmentsByCourseId(Long courseId);

    Assignment updateAssignment(Long id, Assignment updatedAssignment);

    void deleteAssignment(Long id);
}
