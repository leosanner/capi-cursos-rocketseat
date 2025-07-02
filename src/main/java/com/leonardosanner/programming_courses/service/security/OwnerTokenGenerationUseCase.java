package com.leonardosanner.programming_courses.service.security;

import com.leonardosanner.programming_courses.dto.owner.AuthOwnerDTO;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import com.leonardosanner.programming_courses.exception.OwnerNotFoundException;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class OwnerTokenGenerationUseCase {

    @Value("${spring.security.jwt.owner.secret}")
    private String secretKey;

    @Value("${spring.security.jwt.issuer}")
    private String issuer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OwnerRepository ownerRepository;

    public ResponseEntity<Object> execute(AuthOwnerDTO authOwnerDTO) {

        OwnerEntity owner = this.verifyOwnerExistence(authOwnerDTO.getUsername());

        if (verifyPasswordMatches(authOwnerDTO.getPassword(), owner.getPassword())) {
            String token = tokenGeneration(authOwnerDTO.getUsername());

            return ResponseEntity.ok().body(token);
        }

        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Invalid Credentials.");
    }

    private OwnerEntity verifyOwnerExistence(String ownerName) {
        OwnerEntity owner =  ownerRepository.findByName(ownerName).orElseThrow(()->
                new OwnerNotFoundException("Owner/User not found"));

        return owner;
    }

    private boolean verifyPasswordMatches(String ownerPasswordInformed, String ownerPasswordEntity){
        return passwordEncoder.matches(ownerPasswordInformed, ownerPasswordEntity);
    }

    private String tokenGeneration(String ownerName) {
        Instant currentTime = Instant.now();
        SecretKey secret = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jws = Jwts.builder()
                .subject(ownerName)
                .signWith(secret)
                .issuedAt(Date.from(currentTime))
                .expiration(Date.from(currentTime.plus(1, ChronoUnit.HOURS)))
                .claim("role", "owner")
                .issuer(issuer)
                .compact();

        return jws;
    }
}
