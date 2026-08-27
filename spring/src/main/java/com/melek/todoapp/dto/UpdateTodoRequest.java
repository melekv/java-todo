package com.melek.todoapp.dto;

import com.melek.todoapp.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateTodoRequest(
    @NotBlank
    @Size(min = 2, max = 20)
    String title,

    String description,

    @NotNull
    Status status,

    @NotNull
    UUID categoryId
) {
}
