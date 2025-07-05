package com.leonardosanner.programming_courses.dto.owner;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerJWTCredentialsDTO {

    private String email;
    private String roles;
}
