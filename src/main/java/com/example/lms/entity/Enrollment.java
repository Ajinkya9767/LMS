package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "enrollment_status")
    private EnrollmentStatus enrollmentStatus;

    @Column(name = "grade", nullable = false)
    private String grade;

    public enum EnrollmentStatus {
        ENROLLED, COMPLETED, DROPPED
    }
}