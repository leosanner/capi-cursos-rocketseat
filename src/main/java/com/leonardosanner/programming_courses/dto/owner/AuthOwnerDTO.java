package com.leonardosanner.programming_courses.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthOwnerDTO {

    private String username;
    private String password;
}
