package com.leonardosanner.programming_courses.controller.owner;

import com.leonardosanner.programming_courses.dto.owner.AuthOwnerDTO;
import com.leonardosanner.programming_courses.entity.owner.OwnerEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody OwnerEntity ownerEntity) {
        return null;
    }

    @GetMapping("/auth/token")
    public ResponseEntity<Object> generateToken(@RequestBody AuthOwnerDTO authOwnerDTO) {
        return null;
    }
}
