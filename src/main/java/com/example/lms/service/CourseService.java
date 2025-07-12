package com.example.lms.service;

import com.example.lms.entity.Course;
import com.example.lms.model.GenericResponse;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    GenericResponse<Course> createCourse(Course course);

    List<Course> getAllCourses();

    Optional<Course> getCourseById(Long id);

    GenericResponse<Course> updateCourse(Long id, Course updatedCourse);

    boolean deleteCourse(Long id);
}
