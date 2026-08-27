package com.melek.todoapp.exception;

public class TodoNotFoundException extends RuntimeException {
    public static final String message = "Todo not found";

    public TodoNotFoundException() {
        super(message);
    }
}
