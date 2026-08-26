package com.melek.todoapp.mapper;

import com.melek.todoapp.dto.CategoryDto;
import com.melek.todoapp.entity.Category;

public class CategoryMapper {

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
            category.getId(),
            category.getName(),
            category.getCreatedAt(),
            category.getUpdatedAt()
        );
    }
}
