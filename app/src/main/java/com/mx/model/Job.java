package com.mx.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Jobs")
@Setter
@Getter
public class Job {
    @Id
    @Column(name = "JOB_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "JOB_DESC")
    private String jobDesc;

    @Column(name = "IS_PUBLISHED")
    private Integer isPublished;

    @Column(name = "TIME_CREATED", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime timeCreated;

    @Column(name = "TIME_PUBLISHED", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime timePublished;

    @Column(name = "TIME_CLOSED", columnDefinition = "DATETIME DEFAULT NULL")
    @CreationTimestamp
    private LocalDateTime timeClosed;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
