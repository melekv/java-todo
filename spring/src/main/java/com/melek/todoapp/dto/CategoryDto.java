package com.melek.todoapp.dto;

import java.time.Instant;
import java.util.UUID;

public record CategoryDto(
    UUID id,
    String name,
    Instant createdAt,
    Instant updatedAt
) {
}
