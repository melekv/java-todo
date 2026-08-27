package com.melek.todoapp.dto;

import com.melek.todoapp.enums.Status;

import java.time.Instant;
import java.util.UUID;

public record TodoDto(
    UUID id,
    String title,
    String description,
    Status status,
    UUID userId,
    UUID categoryId,
    Instant createdAt,
    Instant updatedAt
) {
}
