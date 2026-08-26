package com.melek.todoapp.service;

import com.melek.todoapp.dto.CreateCategoryRequest;
import com.melek.todoapp.entity.Category;
import com.melek.todoapp.exception.CategoryAlreadyExistsException;
import com.melek.todoapp.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void Category_Should_Be_Created() {

        var request = new CreateCategoryRequest(
            "Travel"
        );

        when(categoryRepository.existsByName(request.name()))
            .thenReturn(false);

        var category = new Category(
            "Travel"
        );

        when(categoryRepository.save(any(Category.class)))
            .thenReturn(category);

        var result = categoryService.create(request);

        assertEquals("Travel", result.getName());

        verify(categoryRepository).existsByName(request.name());
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    public void Should_Throw_Exception_When_Category_Already_Exists() {

        var request = new CreateCategoryRequest(
            "Travel"
        );

        when(categoryRepository.existsByName(request.name()))
            .thenReturn(true);

        assertThrows(
            CategoryAlreadyExistsException.class,
            () -> categoryService.create(request)
        );

        verify(categoryRepository).existsByName(request.name());
        verify(categoryRepository, never()).save(any());
    }

    @Test
    public void Category_Should_Be_Deleted() {

        var id = UUID.randomUUID();

        categoryService.delete(id);

        verify(categoryRepository).deleteById(id);
    }
}
