package com.melek.todoapp.mapper;

import com.melek.todoapp.dto.UserDto;
import com.melek.todoapp.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.isActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
