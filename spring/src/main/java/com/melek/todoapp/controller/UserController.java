package com.melek.todoapp.controller;

import com.melek.todoapp.dto.CreateUserRequest;
import com.melek.todoapp.dto.UpdateUserRequest;
import com.melek.todoapp.dto.UserDto;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.mapper.UserMapper;
import com.melek.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> list() {
        List<User> users = userService.list();

        List<UserDto> response = users.stream()
            .map(UserMapper::toDto)
            .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(
        @Valid @RequestBody CreateUserRequest request
    ) {
        User user = userService.create(request);

        UserDto response = UserMapper.toDto(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateUserRequest request
    ) {
        User user = userService.update(id, request);

        UserDto response = UserMapper.toDto(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
