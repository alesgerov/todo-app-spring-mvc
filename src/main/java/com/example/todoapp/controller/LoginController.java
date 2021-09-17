package com.example.todoapp.controller;

import com.example.todoapp.form.LoginForm;
import com.example.todoapp.service.LoginService;
import com.example.todoapp.service.UserService;
import com.example.todoapp.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/login", "/login"})
public class LoginController {


    @Autowired
    private LoginValidator validator;

    @Autowired
    private LoginService loginService;


    @InitBinder
    public void dataBind(WebDataBinder dataBinder) {
        if (dataBinder.getTarget() != null
                && dataBinder.getTarget().getClass().equals(LoginForm.class)) {
            dataBinder.setValidator(validator);
        }
    }


    @GetMapping
    public ModelAndView login(@RequestParam(value = "error",defaultValue = "false") boolean loginError){

        ModelAndView modelAndView=new ModelAndView();
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        if (loginError){
            modelAndView.addObject("error","Username or password is false.");
        }
        LoginForm loginForm=new LoginForm();
        modelAndView.setViewName("login");
        modelAndView.addObject("form",loginForm);
        return modelAndView;
    }


    @PostMapping
    public ModelAndView loginPost(@Validated @ModelAttribute(name = "form") LoginForm form, BindingResult result,HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        String reffere=request.getHeader("Referer");
        if (result.hasErrors()){
            modelAndView.setViewName("login");
        }else if(loginService.checkLogin(form)) {
            modelAndView.setViewName("redirect:/");
        }else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
