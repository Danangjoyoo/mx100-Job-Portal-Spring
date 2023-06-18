package com.mx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.model.Role;
import com.mx.model.User;
import com.mx.schema.PostAuthSchema;
import com.mx.schema.PostCreateUserSchema;
import com.mx.schema.ResponseAuthSchema;
import com.mx.schema.SuccessSchema;
import com.mx.service.RoleService;
import com.mx.service.UserService;
import com.mx.util.HashPasswordChecker;
import com.mx.util.JwtUtil;
import com.mx.util.PasswordHashing;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final JwtUtil jwt;

    public UserController(UserService userService, RoleService roleService, JwtUtil jwtUtil){
        this.userService = userService;
        this.roleService = roleService;
        this.jwt = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseAuthSchema login(@RequestBody PostAuthSchema postSchema) {
        ResponseAuthSchema responseSchema = new ResponseAuthSchema(null);
        Optional<User> optUser = userService.findUser(postSchema.getUsername());

        if (!optUser.isPresent()) {
            return responseSchema;
        }

        User user = optUser.get();
        if (!HashPasswordChecker.check(postSchema.getPassword(), user.getPassword())){
            return responseSchema;
        }

        String token = jwt.generateAccessToken(postSchema.getUsername());
        responseSchema.setToken(token);

        return responseSchema;
    }

    @PostMapping("/create")
    public SuccessSchema createUser(@RequestBody PostCreateUserSchema postSchema){
        User new_user = new User();
        new_user.setFullname(postSchema.getFullname());
        new_user.setUsername(postSchema.getUsername());
        new_user.setPassword(
            PasswordHashing.hash(postSchema.getPassword())
        );
        new_user.setTimezone(String.format("UTC%s", postSchema.getTimezone()));

        Optional<Role> optRole = roleService.getSpecificRole(1l);
        if (optRole.isPresent()){
            new_user.setRole(optRole.get());
            userService.createUser(new_user);
            return new SuccessSchema(1);
        } else {
            return new SuccessSchema(0);
        }

    }

}
