package com.example.todoapp.security;

import com.example.todoapp.model.TodoUser;
import com.example.todoapp.service.LoginService;
import com.example.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    private UserService service;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TodoUser user=service.getUserByUsername(s);
        return new UserPrincipal(user);
    }
}
