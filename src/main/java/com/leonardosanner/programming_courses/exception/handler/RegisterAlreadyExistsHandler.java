package com.leonardosanner.programming_courses.exception.handler;

import com.leonardosanner.programming_courses.exception.RegisterAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegisterAlreadyExistsHandler {

    @ExceptionHandler(RegisterAlreadyExistsException.class)
    public ResponseEntity<Object> registerAlreadyExistsException(RegisterAlreadyExistsException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
