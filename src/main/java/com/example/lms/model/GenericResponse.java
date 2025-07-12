package com.example.lms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {

    private T data;
    private String status;
    private String description;
    private String errorMessage;
    private Integer statusCode;
}
