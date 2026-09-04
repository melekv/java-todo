package com.melek.todoapp.service;

import com.melek.todoapp.dto.CreateTodoRequest;
import com.melek.todoapp.dto.UpdateTodoRequest;
import com.melek.todoapp.entity.Category;
import com.melek.todoapp.entity.Todo;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.enums.Status;
import com.melek.todoapp.exception.CategoryNotFoundException;
import com.melek.todoapp.exception.UserNotFoundException;
import com.melek.todoapp.repository.CategoryRepository;
import com.melek.todoapp.repository.TodoRepository;
import com.melek.todoapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void Todo_Should_Be_Created() {
        var userId = UUID.randomUUID();
        var categoryId = UUID.randomUUID();

        var request = new CreateTodoRequest(
            "First",
            "The first todo",
            userId,
            categoryId
        );

        var user = new User(
            "John",
            "Doe",
            "john.doe@example.com",
            "password"
        );

        var category = new Category(
            "Travel"
        );

        var todo = new Todo(
            "First",
            "The first todo",
            user,
            category
        );

        when(userRepository.findById(userId))
            .thenReturn(Optional.of(user));

        when(categoryRepository.findById(categoryId))
            .thenReturn(Optional.of(category));

        when(todoRepository.save(any(Todo.class)))
            .thenReturn(todo);

        var result = todoService.create(request);

        assertEquals("First", result.getTitle());
        assertEquals("The first todo", result.getDescription());
        assertEquals(user, result.getUser());
        assertEquals(category, result.getCategory());

        verify(userRepository).findById(userId);
        verify(categoryRepository).findById(categoryId);
        verify(todoRepository).save(any(Todo.class));
    }

    @Test
    public void Todo_Throw_Exception_When_User_Does_Not_Exist() {
        var userId = UUID.randomUUID();
        var categoryId = UUID.randomUUID();

        var request = new CreateTodoRequest(
            "First",
            "The first todo",
            userId,
            categoryId
        );

        when(userRepository.findById(userId))
            .thenReturn(Optional.empty());

        assertThrows(
            UserNotFoundException.class,
            () -> todoService.create(request)
        );
    }

    @Test
    public void Todo_Throw_Exception_When_Category_Does_Not_Exist() {
        var userId = UUID.randomUUID();
        var categoryId = UUID.randomUUID();

        var request = new CreateTodoRequest(
            "First",
            "The first todo",
            userId,
            categoryId
        );

        var user = new User(
            "John",
            "Doe",
            "john.doe@example.com",
            "password"
        );

        when(userRepository.findById(userId))
            .thenReturn(Optional.of(user));

        when(categoryRepository.findById(categoryId))
            .thenReturn(Optional.empty());

        assertThrows(
            CategoryNotFoundException.class,
            () -> todoService.create(request)
        );
    }

    @Test
    public void Todo_Should_Be_Updated() {
        var todoId = UUID.randomUUID();
        var categoryId = UUID.randomUUID();

        var request = new UpdateTodoRequest(
            "Next",
            "Another",
            Status.IN_PROGRESS,
            categoryId
        );

        var user = new User(
            "John",
            "Doe",
            "john.doe@example.com",
            "password"
        );

        var oldCategory = new Category(
            "Travel"
        );

        var newCategory = new Category(
            "Work"
        );

        newCategory.setId(categoryId);

        var todo = new Todo(
            "First",
            "The first todo",
            user,
            oldCategory
        );

        when(todoRepository.findById(todoId))
            .thenReturn(Optional.of(todo));

        when(categoryRepository.findById(categoryId))
            .thenReturn(Optional.of(newCategory));

        when(todoRepository.save(any(Todo.class)))
            .thenReturn(todo);

        var result = todoService.update(todoId, request);

        assertEquals("Next", result.getTitle());
        assertEquals("Another", result.getDescription());
        assertEquals(Status.IN_PROGRESS, result.getStatus());
        assertEquals(newCategory, result.getCategory());

        verify(todoRepository).save(any(Todo.class));
    }

    @Test
    public void Todo_Should_Be_Deleted() {
        var todoId = UUID.randomUUID();

        todoService.delete(todoId);

        verify(todoRepository).deleteById(todoId);
    }
}
