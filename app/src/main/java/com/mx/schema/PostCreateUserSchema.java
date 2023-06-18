package com.mx.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostCreateUserSchema {
    private String fullname;
    private String username;
    private String password;
    private Integer timezone;
    private Integer role;
}
