package com.melek.todoapp.service;

import com.melek.todoapp.dto.CreateUserRequest;
import com.melek.todoapp.dto.UpdateUserRequest;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.exception.UserAlreadyExistsException;
import com.melek.todoapp.exception.UserNotFoundException;
import com.melek.todoapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void User_Should_Be_Created() {

        var request = new CreateUserRequest(
            "John",
            "Doe",
            "john.doe@example.com"
        );

        when(userRepository.existsByEmail(request.email()))
            .thenReturn(false);

        var user = new User(
            "John",
            "Doe",
            "john.doe@example.com"
        );

        when(userRepository.save(any(User.class)))
            .thenReturn(user);

        var result = userService.create(request);

        assertEquals("john.doe@example.com", result.getEmail());

        verify(userRepository).existsByEmail(request.email());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void Should_Throw_Exception_When_User_Already_Exists() {

        var request = new CreateUserRequest(
            "John",
            "Doe",
            "john.doe@example.com"
        );

        when(userRepository.existsByEmail(request.email()))
            .thenReturn(true);

        assertThrows(
            UserAlreadyExistsException.class,
            () -> userService.create(request)
        );

        verify(userRepository).existsByEmail(request.email());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void User_Should_Be_Updated() {

        var id = UUID.randomUUID();

        var request = new UpdateUserRequest(
            "James",
            "Rust",
            false
        );

        var user = new User(
            "John",
            "Doe",
            "john.due@example.com"
        );

        when(userRepository.findById(id))
            .thenReturn(Optional.of(user));

        when(userRepository.save(any(User.class)))
            .thenReturn(user);

        var result = userService.update(id, request);

        assertEquals("James", result.getFirstName());
        assertEquals("Rust", result.getLastName());
        assertFalse(result.isActive());

        verify(userRepository).save(user);
    }

    @Test
    public void Should_Throw_Exception_When_User_Does_Not_Exist() {

        var id = UUID.randomUUID();

        var request = new UpdateUserRequest(
            "James",
            "Rust",
            false
        );

        when(userRepository.findById(id))
            .thenThrow(UserNotFoundException.class);

        assertThrows(
            UserNotFoundException.class,
            () -> userService.update(id, request)
        );
    }

    @Test
    public void User_Should_Be_Deleted() {

        var id = UUID.randomUUID();

        userService.delete(id);

        verify(userRepository).deleteById(id);
    }
}
