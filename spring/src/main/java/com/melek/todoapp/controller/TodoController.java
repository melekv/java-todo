package com.melek.todoapp.controller;

import com.melek.todoapp.dto.CreateTodoRequest;
import com.melek.todoapp.dto.TodoDto;
import com.melek.todoapp.dto.UpdateTodoRequest;
import com.melek.todoapp.entity.Todo;
import com.melek.todoapp.mapper.TodoMapper;
import com.melek.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> list() {
        List<Todo> todos = todoService.list();

        List<TodoDto> response = todos
            .stream()
            .map(TodoMapper::toDto)
            .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TodoDto> create(
        @Valid @RequestBody CreateTodoRequest request
    ) {
        Todo todo = todoService.create(request);

        TodoDto response = TodoMapper.toDto(todo);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateTodoRequest request
    ) {
        Todo todo = todoService.update(id, request);

        TodoDto response = TodoMapper.toDto(todo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
