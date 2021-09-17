package com.example.todoapp.service;


import com.example.todoapp.form.RegistrationForm;
import com.example.todoapp.model.TodoUser;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  PasswordService passwordService;

    @Autowired
    private UserRepository repository;

    public TodoUser getUserByUsername(String username){
        Optional<TodoUser> user=repository.findTodoUserByUsername(username);
        return user.orElse(null);
    }

    public TodoUser getUserByEmail(String email){
        Optional<TodoUser> user=repository.findTodoUserByEmail(email);
        return user.orElse(null);
    }

    public TodoUser saveUser(RegistrationForm form){
        TodoUser user=new TodoUser();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(passwordService.hashPassword(form.getPassword()));
        return repository.save(user);
    }
    public TodoUser saveUser(TodoUser user){
        return repository.save(user);
    }

    public TodoUser getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        return getUserByUsername(username);
    }

    public boolean isActivated(TodoUser user){
        return user.getStatus().equals("ACTIVATED");
    }
}
