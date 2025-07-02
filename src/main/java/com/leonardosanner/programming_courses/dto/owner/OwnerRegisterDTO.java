package com.leonardosanner.programming_courses.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OwnerRegisterDTO {

    private String name;
    private String email;
    private String password;
}
