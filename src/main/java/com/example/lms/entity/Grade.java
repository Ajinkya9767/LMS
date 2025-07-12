package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "submission_id", nullable = false)
    private Long submissionId;

    @Column(length = 10)
    private String grade;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "graded_at", updatable = false)
    private LocalDateTime gradedAt;

    @PrePersist
    protected void onCreate() {
        this.gradedAt = LocalDateTime.now();
    }
}