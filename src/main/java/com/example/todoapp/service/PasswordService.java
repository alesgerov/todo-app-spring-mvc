package com.example.todoapp.service;

import com.example.todoapp.repository.PasswordRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordService implements PasswordRepository {


    @Override
    public String hashPassword(String password) {

        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String hashedPassword, String rawPassword) {
        return BCrypt.checkpw(rawPassword,hashedPassword);
    }
}
