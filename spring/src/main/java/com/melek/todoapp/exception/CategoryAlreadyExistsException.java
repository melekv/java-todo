package com.melek.todoapp.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    private static final String message = "Category already exists";

    public CategoryAlreadyExistsException() {
        super(message);
    }
}
