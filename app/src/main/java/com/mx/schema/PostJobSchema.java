package com.mx.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostJobSchema {
    private String title;
    private String desc;
    private boolean published;
}
