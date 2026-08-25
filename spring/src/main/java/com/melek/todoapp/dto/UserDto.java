package com.melek.todoapp.dto;

import java.time.Instant;
import java.util.UUID;

public record UserDto(
    UUID id,
    String firstName,
    String lastName,
    String email,
    Boolean active,
    Instant createdAt,
    Instant updatedAt
) {
}
