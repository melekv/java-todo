package com.melek.todoapp.mapper;

import com.melek.todoapp.dto.TodoDto;
import com.melek.todoapp.entity.Todo;

public class TodoMapper {

    public static TodoDto toDto(Todo todo) {
        return new TodoDto(
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            todo.getStatus(),
            todo.getUser().getId(),
            todo.getCategory().getId(),
            todo.getCreatedAt(),
            todo.getUpdatedAt()
        );
    }
}
