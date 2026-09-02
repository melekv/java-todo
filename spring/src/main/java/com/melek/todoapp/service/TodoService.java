package com.melek.todoapp.service;

import com.melek.todoapp.dto.CreateTodoRequest;
import com.melek.todoapp.dto.UpdateTodoRequest;
import com.melek.todoapp.entity.Category;
import com.melek.todoapp.entity.Todo;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.exception.CategoryNotFoundException;
import com.melek.todoapp.exception.TodoNotFoundException;
import com.melek.todoapp.exception.UserNotFoundException;
import com.melek.todoapp.repository.CategoryRepository;
import com.melek.todoapp.repository.TodoRepository;
import com.melek.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TodoRepository todoRepository;

    public TodoService(
        UserRepository userRepository,
        CategoryRepository categoryRepository,
        TodoRepository todoRepository
    ) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.todoRepository = todoRepository;
    }

    public List<Todo> list() {
        return todoRepository.findAll();
    }

    public Todo get(UUID id) {
        return todoRepository.findById(id)
            .orElseThrow(TodoNotFoundException::new);
    }

    public Todo create(CreateTodoRequest createTodoRequest) {
        User user = userRepository.findById(createTodoRequest.userId())
            .orElseThrow(UserNotFoundException::new);

        Category category = categoryRepository.findById(createTodoRequest.categoryId())
            .orElseThrow(CategoryNotFoundException::new);

        Todo todo = new Todo(
            createTodoRequest.title(),
            createTodoRequest.description(),
            user,
            category
        );

        return todoRepository.save(todo);
    }

    public Todo update(UUID id, UpdateTodoRequest updateTodoRequest) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(TodoNotFoundException::new);

        Category category = categoryRepository.findById(updateTodoRequest.categoryId())
            .orElseThrow(CategoryNotFoundException::new);

        todo.setTitle(updateTodoRequest.title());
        todo.setDescription(updateTodoRequest.description());
        todo.setStatus(updateTodoRequest.status());
        todo.setCategory(category);

        return todoRepository.save(todo);
    }

    public void delete(UUID id) {
        todoRepository.deleteById(id);
    }
}
