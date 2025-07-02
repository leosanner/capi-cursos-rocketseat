package com.leonardosanner.programming_courses.controller.owner;

import com.leonardosanner.programming_courses.dto.owner.AuthOwnerDTO;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import com.leonardosanner.programming_courses.repository.OwnerRepository;
import com.leonardosanner.programming_courses.service.useCases.owner.CreateOwnerUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private CreateOwnerUseCase createOwnerUseCase;

    @Autowired
    private OwnerRepository ownerRepository;


    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody OwnerEntity ownerEntity) {

        return null;
    }

    @GetMapping("/")
    public String teste() {
        System.out.println("Entrou no servlet");

        return "End of the request";
    }

    @GetMapping("/auth/token")
    public ResponseEntity<Object> generateToken(@RequestBody AuthOwnerDTO authOwnerDTO) {
        return null;
    }
}
