package com.example.todoapp.repository;

import com.example.todoapp.form.RegistrationForm;
import com.example.todoapp.model.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<TodoUser,Long> {
    Optional<TodoUser> findTodoUserByUsername(String username);
    Optional<TodoUser> findTodoUserByEmail(String email);
}
