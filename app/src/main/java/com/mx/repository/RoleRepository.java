package com.mx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
