package com.mx.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Proposals")
@Getter
@Setter
public class Proposal {
    @Id
    @Column(name = "PROPOSAL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProposalId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;

    @Column(name = "TIME_CREATED", columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime timeCreated;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
