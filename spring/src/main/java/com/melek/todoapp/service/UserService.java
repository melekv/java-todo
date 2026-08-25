package com.melek.todoapp.service;

import com.melek.todoapp.exception.UserAlreadyExistsException;
import com.melek.todoapp.dto.CreateUserRequest;
import com.melek.todoapp.dto.UpdateUserRequest;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.exception.UserNotFoundException;
import com.melek.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User create(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.email())) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(
            createUserRequest.firstName(),
            createUserRequest.lastName(),
            createUserRequest.email()
        );

        return userRepository.save(user);
    }

    public User update(UUID id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        user.setFirstName(updateUserRequest.firstName());
        user.setLastName(updateUserRequest.lastName());
        user.setActive(updateUserRequest.active());

        return userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
