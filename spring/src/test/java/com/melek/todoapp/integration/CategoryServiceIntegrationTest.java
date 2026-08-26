package com.melek.todoapp.integration;

import com.melek.todoapp.dto.CreateCategoryRequest;
import com.melek.todoapp.repository.CategoryRepository;
import com.melek.todoapp.service.CategoryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
@Transactional
public class CategoryServiceIntegrationTest {

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
    private CategoryService categoryService;

    @Autowired
    protected CategoryRepository categoryRepository;

    @Test
    public void Category_Should_Be_Created() {

        var request = new CreateCategoryRequest(
            "Travel"
        );

        var result = categoryService.create(request);

        assertEquals(
            "Travel",
            result.getName()
        );

        var savedCategory = categoryRepository.findByName("Travel");

        assertTrue(savedCategory.isPresent());
    }

    @Test
    public void Category_Should_Be_Deleted() {

        var request = new CreateCategoryRequest(
            "Travel"
        );

        var result = categoryService.create(request);

        categoryService.delete(result.getId());

        var deletedCategory = categoryRepository.findByName("Travel");

        assertTrue(deletedCategory.isEmpty());
    }
}
