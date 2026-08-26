package com.melek.todoapp.service;

import com.melek.todoapp.dto.CreateCategoryRequest;
import com.melek.todoapp.entity.Category;
import com.melek.todoapp.exception.CategoryAlreadyExistsException;
import com.melek.todoapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category create(CreateCategoryRequest createCategoryRequest) {
        if (categoryRepository.existsByName(createCategoryRequest.name())) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = new Category(
            createCategoryRequest.name()
        );

        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }
}
