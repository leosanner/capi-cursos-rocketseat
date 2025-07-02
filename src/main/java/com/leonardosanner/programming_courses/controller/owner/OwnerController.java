package com.leonardosanner.programming_courses.controller.owner;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @PostMapping("/")
    public ResponseEntity<Object> create() {
        return null;
    }

    @GetMapping("/auth/token")
    public ResponseEntity<Object> generateToken() {
        return null;
    }
}
