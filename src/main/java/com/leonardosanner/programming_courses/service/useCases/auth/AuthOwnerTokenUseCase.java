package com.leonardosanner.programming_courses.service.useCases.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class AuthOwnerTokenUseCase {

    @Value("${spring.security.jwt.owner.secret}")
    private String secret;

    public void execute(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Jwts.parser()
                .verifyWith(secretKey); // parei aqui
    }
}
