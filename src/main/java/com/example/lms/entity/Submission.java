package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assignment_id", nullable = false)
    private Long assignmentId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "submission_date", updatable = false)
    private LocalDateTime submissionDate;

    @PrePersist
    protected void onCreate() {
        this.submissionDate = LocalDateTime.now();
    }
}