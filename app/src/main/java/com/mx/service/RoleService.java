package com.mx.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mx.model.Role;
import com.mx.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getSpecificRole(Long roleId){
        return this.roleRepository.findById(roleId);
    }

}
