package com.example.demo.exception;

public class CustomInternalServerErrorException extends RuntimeException {
    public CustomInternalServerErrorException(String message) {
        super(message);
    }
}
