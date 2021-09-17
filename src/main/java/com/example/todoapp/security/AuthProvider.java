package com.example.todoapp.security;


import com.example.todoapp.model.TodoUser;
import com.example.todoapp.service.LoginService;
import com.example.todoapp.service.PasswordService;
import com.example.todoapp.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log
@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println(authentication.toString());
        UsernamePasswordAuthenticationToken auth=(UsernamePasswordAuthenticationToken) authentication;
        String  username=auth.getName();
        String password= String.valueOf(auth.getCredentials());
        String pw=password;
        log.info("llllll"+username+pw);
        TodoUser user=userService.getUserByUsername(username);

        if (passwordService.checkPassword(user.getPassword(),pw)){
            return new UsernamePasswordAuthenticationToken(username,password);
        }

        log.info("\nolmadi");
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
