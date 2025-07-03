package com.leonardosanner.programming_courses.controller.owner;

import com.leonardosanner.programming_courses.dto.owner.AuthOwnerDTO;
import com.leonardosanner.programming_courses.dto.owner.OwnerRegisterDTO;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import com.leonardosanner.programming_courses.service.security.OwnerTokenGenerationUseCase;
import com.leonardosanner.programming_courses.service.useCases.owner.CreateOwnerUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private CreateOwnerUseCase createOwnerUseCase;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerTokenGenerationUseCase ownerTokenGenerationUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody OwnerEntity ownerEntity) {
        OwnerRegisterDTO  ownerRegisterDTO = OwnerRegisterDTO.builder()
                .name(ownerEntity.getName())
                .email(ownerEntity.getEmail())
                .password(ownerEntity.getPassword())
                .build();

        createOwnerUseCase.execute(ownerRegisterDTO);

        return ResponseEntity.ok().body("Owner created with success.");
    }

    @GetMapping("/")
    public ResponseEntity<List<OwnerEntity>> getAllUsers() {
        return ResponseEntity.ok().body(ownerRepository.findAll());
    }

    @GetMapping("/auth/token")
    public ResponseEntity<Object> generateToken(@RequestBody AuthOwnerDTO authOwnerDTO) {
        System.out.println("Get into servlet...");
        return ownerTokenGenerationUseCase.execute(authOwnerDTO);
    }
}
