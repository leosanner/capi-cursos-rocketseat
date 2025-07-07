package com.leonardosanner.programming_courses.exception.handler;


import com.leonardosanner.programming_courses.dto.GenericErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RuntimeHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.badRequest().body(
                new GenericErrorDTO(RuntimeException.class.getName(),
                        e.getMessage())
        );
    }
}
