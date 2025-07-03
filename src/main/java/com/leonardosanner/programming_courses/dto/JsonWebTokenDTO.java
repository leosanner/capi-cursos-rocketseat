package com.leonardosanner.programming_courses.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class JsonWebTokenDTO {

    private String token;
    private List<String> roles;
    private String issuer;
    private LocalDateTime expiresAt;
}
