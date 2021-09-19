package com.example.todoapp.controller;


import com.example.todoapp.form.PasswordForm;
import com.example.todoapp.model.TodoUser;
import com.example.todoapp.service.PasswordService;
import com.example.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;



    @GetMapping({"/password/change","/password/change/"})
    public ModelAndView changePassword(){
        ModelAndView modelAndView=new ModelAndView();
        PasswordForm form =new PasswordForm();
        modelAndView.setViewName("change-password");
        modelAndView.addObject("form",form);
        return modelAndView;
    }

    @PostMapping({"/password/change","/password/change/"})
    public ModelAndView changePasswordPost(@Valid @ModelAttribute(name = "form") PasswordForm form,
                                           BindingResult result,
                                           HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        TodoUser user=userService.getCurrentUser();
        boolean isEqual=passwordService.checkPassword(user.getPassword(),form.getPassword());
        if (isEqual && !result.hasErrors()){
            user.setPassword(passwordService.hashPassword(form.getNewPassword()));
            userService.saveUser(user);
            modelAndView.setViewName("redirect:/");
        }else if (!isEqual){
            result.rejectValue("password","oldPassword.wrong");
            modelAndView.setViewName("change-password");
        }else {
            modelAndView.addObject("error",result.getAllErrors().get(0).getDefaultMessage());
            modelAndView.setViewName("change-password");
        }
        return modelAndView;
    }
}
