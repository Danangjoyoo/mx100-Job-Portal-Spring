package com.mx.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostAuthSchema {
    private String username;
    private String password;
}
