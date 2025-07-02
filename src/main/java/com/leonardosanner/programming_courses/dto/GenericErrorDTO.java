package com.leonardosanner.programming_courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericErrorDTO {

    private String field;
    private String message;
}
