package com.leonardosanner.programming_courses.service.security;

import com.leonardosanner.programming_courses.dto.JsonWebTokenDTO;
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
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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

    // primary method
    public ResponseEntity<Object> execute(AuthOwnerDTO authOwnerDTO) {

        String ownerPassword = this.verifyOwnerExistence(authOwnerDTO.getEmail()).getPassword();

        if (verifyPasswordMatches(authOwnerDTO.getPassword(), ownerPassword)) {
            JsonWebTokenDTO token = tokenGeneration(authOwnerDTO.getEmail());

            return ResponseEntity.ok().body(token);
        }

        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Mismatch between passwords.");
    }

    // owner existence
    private OwnerEntity verifyOwnerExistence(String ownerEmail) {
        OwnerEntity owner =  ownerRepository.findByEmail(ownerEmail).orElseThrow(()->
                new OwnerNotFoundException("Owner/User not found"));

        return owner;
    }

    // password matches
    private boolean verifyPasswordMatches(String ownerPasswordInformed, String ownerPasswordEntity){
        return passwordEncoder.matches(ownerPasswordInformed, ownerPasswordEntity);
    }

    // JWT generation
    private JsonWebTokenDTO tokenGeneration(String ownerEmail) {
        Instant currentTime = Instant.now();
        Instant expirationTime = currentTime.plus(1, ChronoUnit.HOURS);
        SecretKey secret = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jws = Jwts.builder()
                .subject(ownerEmail)
                .signWith(secret)
                .issuedAt(Date.from(currentTime))
                .expiration(Date.from(expirationTime))
                .claim("role", "owner")
                .issuer(issuer)
                .compact();

        ZoneId zoneId = ZoneId.systemDefault();

        return JsonWebTokenDTO.builder()
                .token(jws)
                .expiresAt(LocalDateTime.ofInstant(expirationTime, zoneId))
                .roles(Arrays.asList("owner"))
                .issuer(issuer)
                .build();
    }
}
