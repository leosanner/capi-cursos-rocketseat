package com.leonardosanner.programming_courses.exception.handler;

import com.leonardosanner.programming_courses.dto.GenericErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MethodNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<GenericErrorDTO>> methodArgumentNotValidExceptionHandler
            (MethodArgumentNotValidException e) {

        List<GenericErrorDTO> errorDTOS = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err ->
                errorDTOS.add(new GenericErrorDTO(err.getField(), err.getDefaultMessage()))
        );

        return ResponseEntity.badRequest().body(errorDTOS);
    }
}
