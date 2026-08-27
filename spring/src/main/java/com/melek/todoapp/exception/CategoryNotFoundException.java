package com.melek.todoapp.exception;

public class CategoryNotFoundException extends RuntimeException {
    public static final String message = "Category not found";

    public CategoryNotFoundException() {
        super(message);
    }
}
