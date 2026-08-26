package com.melek.todoapp.controller;

import com.melek.todoapp.dto.*;
import com.melek.todoapp.entity.Category;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.mapper.CategoryMapper;
import com.melek.todoapp.mapper.UserMapper;
import com.melek.todoapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> list() {
        List<Category> categories = categoryService.list();

        List<CategoryDto> response = categories.stream()
            .map(CategoryMapper::toDto)
            .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(
        @Valid @RequestBody CreateCategoryRequest request
    ) {
        Category category = categoryService.create(request);

        CategoryDto response = CategoryMapper.toDto(category);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
