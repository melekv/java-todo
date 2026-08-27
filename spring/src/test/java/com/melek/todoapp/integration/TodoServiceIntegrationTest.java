package com.melek.todoapp.integration;

import com.melek.todoapp.dto.CreateCategoryRequest;
import com.melek.todoapp.dto.CreateTodoRequest;
import com.melek.todoapp.dto.CreateUserRequest;
import com.melek.todoapp.entity.Category;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.enums.Status;
import com.melek.todoapp.repository.TodoRepository;
import com.melek.todoapp.service.CategoryService;
import com.melek.todoapp.service.TodoService;
import com.melek.todoapp.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@Transactional
public class TodoServiceIntegrationTest {

    @Container
    private static MySQLContainer mysql = new MySQLContainer("mysql:8.4")
        .withDatabaseName("todo")
        .withUsername("todo")
        .withPassword("todo");

    @DynamicPropertySource
    private static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void Todo_Should_Be_Created() {
        var user = createUser("john.doe@example.com");
        var category = createCategory("Travel");

        var request = new CreateTodoRequest(
            "First",
            "My first todo",
            user.getId(),
            category.getId()
        );

        var result = todoService.create(request);

        assertEquals("First", result.getTitle());
        assertEquals("My first todo", result.getDescription());
        assertEquals(Status.TODO, result.getStatus());
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(category.getId(), result.getCategory().getId());

        var savedTodo = todoRepository.findByTitle("First");

        assertTrue(savedTodo.isPresent());
    }

    @Test
    public void Todo_Should_Be_Deleted() {
        var user = createUser("john.doe@example.com");
        var category = createCategory("Travel");

        var request = new CreateTodoRequest(
            "First",
            "My first todo",
            user.getId(),
            category.getId()
        );

        var result = todoService.create(request);

        todoService.delete(result.getId());

        var deletedTodo = todoRepository.findByTitle("First");

        assertTrue(deletedTodo.isEmpty());
    }

    private User createUser(String email) {
        var createUserRequest = new CreateUserRequest(
            "John",
            "Doe",
            email
        );

        return userService.create(createUserRequest);
    }

    private Category createCategory(String name) {
        var createCategoryRequest = new CreateCategoryRequest(name);

        return categoryService.create(createCategoryRequest);
    }
}
