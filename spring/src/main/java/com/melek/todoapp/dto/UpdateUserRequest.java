package com.melek.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @NotBlank
    @Size(min = 2, max = 20)
    String firstName,

    @NotBlank
    @Size(min = 2, max = 50)
    String lastName,

    @NotNull
    Boolean active
) {
}
