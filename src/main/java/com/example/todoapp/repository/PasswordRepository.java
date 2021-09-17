package com.example.todoapp.repository;

public interface PasswordRepository {
    String hashPassword(String password);
    boolean checkPassword(String hashedPassword,String rawPassword);
}
