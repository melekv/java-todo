package com.melek.todoapp.repository;

import com.melek.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {

    Optional<Todo> findByTitle(String title);
}
