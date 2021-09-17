package com.example.todoapp.service;


import com.example.todoapp.form.LoginForm;
import com.example.todoapp.form.RegistrationForm;
import com.example.todoapp.model.TodoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class LoginService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserService userService;


    public boolean checkLogin(LoginForm form) {
        if (userService.getUserByUsername(form.getUsername())==null){
            return false;
        }
        TodoUser user=userService.getUserByUsername(form.getUsername());
        return passwordService.checkPassword(user.getPassword(), form.getPassword());
    }

    public boolean checkLogin(String  username,String password) {
        if (userService.getUserByUsername(username)==null){
            return false;
        }
        TodoUser user=userService.getUserByUsername(username);
        return passwordService.checkPassword(user.getPassword(), password);
    }
}
