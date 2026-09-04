package com.melek.todoapp.integration;

import com.melek.todoapp.dto.CreateUserRequest;
import com.melek.todoapp.repository.UserRepository;
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
public class UserServiceIntegrationTest {

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
    private UserService userService;

    @Autowired
    protected UserRepository userRepository;

    @Test
    public void User_Should_Be_Created() {

        var request = new CreateUserRequest(
            "John",
            "Doe",
            "john.doe@example.com",
            "password"
        );

        var result = userService.create(request);

        assertEquals(
            "john.doe@example.com",
            result.getEmail()
        );

        var savedUser = userRepository.findByEmail("john.doe@example.com");

        assertTrue(savedUser.isPresent());
    }

    @Test
    public void User_Should_Be_Deleted() {

        var request = new CreateUserRequest(
            "John",
            "Doe",
            "john.doe@example.com",
            "password"
        );

        var result = userService.create(request);

        userService.delete(result.getId());

        var deletedUser = userRepository.findByEmail("john.doe@example.com");

        assertTrue(deletedUser.isEmpty());
    }
}
