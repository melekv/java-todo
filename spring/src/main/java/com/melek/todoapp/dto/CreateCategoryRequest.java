package com.melek.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
    @NotBlank
    @Size(min = 2, max = 30)
    String name
) {
}
