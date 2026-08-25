package com.melek.todoapp.exception;

public class UserNotFoundException extends RuntimeException {
    public static final String message = "User not found";

    public UserNotFoundException() {
        super(message);
    }
}
