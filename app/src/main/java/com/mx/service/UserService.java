package com.mx.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mx.model.User;
import com.mx.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> findUser(String username){
        return this.userRepository.findSpecificUser(username);
    }

    public void createUser(User user){
        this.userRepository.save(user);
    }
}
