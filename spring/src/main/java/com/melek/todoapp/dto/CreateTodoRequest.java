package com.melek.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateTodoRequest(
    @NotBlank
    @Size(min = 2, max = 20)
    String title,

    String description,

    @NotNull
    UUID userId,

    @NotNull
    UUID categoryId
) {
}
