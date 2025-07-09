package com.leonardosanner.programming_courses.service.useCases.auth;

import com.leonardosanner.programming_courses.dto.owner.OwnerJWTCredentialsDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class AuthRoleOwnerUseCase {

    // receive a jwt -> verify role "owner"

    @Value("${spring.security.jwt.owner.secret}")
    private String secret;

    public OwnerJWTCredentialsDTO execute(String token) {

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Jws<Claims> tokenParsed = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);


        return OwnerJWTCredentialsDTO
                .builder()
                .email(tokenParsed.getPayload().getSubject())
                .roles(tokenParsed.getPayload().get("roles").toString())
                .build();
    }
}
