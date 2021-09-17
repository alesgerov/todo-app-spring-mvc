package com.example.todoapp.form;


import lombok.Data;

@Data
public class RegistrationForm  {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private boolean loginSuccess=false;
}
