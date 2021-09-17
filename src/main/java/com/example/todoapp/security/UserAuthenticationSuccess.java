package com.example.todoapp.security;


import com.example.todoapp.model.TodoUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthenticationSuccess implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserPrincipal userPrincipal=(UserPrincipal) authentication.getPrincipal();
        TodoUser user=userPrincipal.getUser();
        httpServletRequest.getSession().setAttribute("user",user);
        httpServletResponse.sendRedirect("/");
    }
}
