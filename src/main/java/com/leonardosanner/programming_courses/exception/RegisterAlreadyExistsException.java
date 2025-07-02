package com.leonardosanner.programming_courses.exception;

public class RegisterAlreadyExistsException extends RuntimeException {
    public RegisterAlreadyExistsException(String message) {
        super(message);
    }
}
