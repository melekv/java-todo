package com.melek.todoapp.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private static final String message = "User already exists";

    public UserAlreadyExistsException() {
        super(message);
    }
}
