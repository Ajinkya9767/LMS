package com.example.lms.controller;

import com.example.lms.entity.Course;
import com.example.lms.model.GenericResponse;
import com.example.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Create a new course
    @PostMapping
    public ResponseEntity<GenericResponse<Course>> createCourse(@RequestBody Course course) {
        GenericResponse<Course> createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatusCode.valueOf(createdCourse.getStatusCode()));
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update course by ID
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Course>> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        GenericResponse<Course> course = courseService.updateCourse(id, updatedCourse);
        return new ResponseEntity<>(course, HttpStatusCode.valueOf(course.getStatusCode()));
    }

    // Delete course by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean isDeleted = courseService.deleteCourse(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

