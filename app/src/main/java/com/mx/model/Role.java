package com.mx.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Roles")
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;
}
