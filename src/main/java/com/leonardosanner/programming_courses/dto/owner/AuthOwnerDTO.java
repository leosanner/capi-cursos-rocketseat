package com.leonardosanner.programming_courses.dto.owner;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthOwnerDTO {

    @Email
    private String email;

    private String password;
}
