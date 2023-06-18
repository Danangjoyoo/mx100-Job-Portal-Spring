package com.mx.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "Users",
    uniqueConstraints =
        @UniqueConstraint(columnNames = {"USERNAME"})
)
@Setter
@Getter
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FULLNAME")
    private String fullname;

    @Column(name = "USERNAME", columnDefinition = "NOT NULL UNIQUE")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TIMEZONE")
    private String timezone;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

}
