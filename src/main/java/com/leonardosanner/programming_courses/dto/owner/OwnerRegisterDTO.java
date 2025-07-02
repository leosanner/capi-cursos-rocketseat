package com.leonardosanner.programming_courses.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class OwnerRegisterDTO {

    private String name;
    private String email;
    private String password;
}
