package com.example.todoapp.controller;
import com.example.todoapp.form.RegistrationForm;
import com.example.todoapp.model.TodoUser;
import com.example.todoapp.model.UserToken;
import com.example.todoapp.notification.SendMail;
import com.example.todoapp.notification.tokenMaker.CreateToken;
import com.example.todoapp.service.UserService;
import com.example.todoapp.service.UserTokenService;
import com.example.todoapp.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpClient;

@Controller
@RequestMapping({"/registration", "/registration"})
public class RegistrationController {

    @Autowired
    private RegistrationValidator validator;

    @Autowired
    private UserService userService;

    @Autowired
    private SendMail mail;

    @Autowired
    private UserTokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @InitBinder
    public void dataBind(WebDataBinder dataBinder) {
        if (dataBinder.getTarget() != null
                && dataBinder.getTarget().getClass().equals(RegistrationForm.class)) {
            dataBinder.setValidator(validator);
        }
    }



    @GetMapping
    public ModelAndView register(){
        RegistrationForm form=new RegistrationForm();
        ModelAndView modelAndView=new ModelAndView();
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.addObject("form",form);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView registerPost(@Validated @ModelAttribute(name = "form") RegistrationForm form,
                                     BindingResult result,
                                     HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        if (result.hasErrors()){
            modelAndView.setViewName("registration");
        }else {
            TodoUser user=userService.saveUser(form);
            tokenService.sendToken(user,request);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

}
