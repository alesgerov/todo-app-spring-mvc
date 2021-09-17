package com.example.todoapp.controller;


import com.example.todoapp.model.TodoStatus;
import com.example.todoapp.model.TodoUser;
import com.example.todoapp.model.UserToken;
import com.example.todoapp.notification.tokenMaker.CreateToken;
import com.example.todoapp.service.UserService;
import com.example.todoapp.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private UserTokenService tokenService;

    @Autowired
    private UserService userService;

//    @GetMapping({"/","/index"})
//    public  ModelAndView indexPage(){
//        ModelAndView modelAndView=new ModelAndView("index");
//        modelAndView.addObject("token", CreateToken.createToken());
//        return modelAndView;
//    }


    @GetMapping({"/activate","/activate/"})
    public ModelAndView token(@RequestParam(name = "token") String token){
        ModelAndView modelAndView=new ModelAndView("token");
        Optional<UserToken> userToken =tokenService.getToken(token);
        if (userToken.isPresent()){
            TodoUser user=userToken.get().getUser();
            if (tokenService.checkToken(token) && user.getStatus().equals("WAITING")){
                modelAndView.addObject("token","Activated");
                modelAndView.addObject("flag",true);
                user.setStatus(TodoStatus.ACTIVATED);
                userService.saveUser(user);
            }else if (!user.getStatus().equals("WAITING")){
                modelAndView.addObject("token","Already activated");
            }
        }else {
            modelAndView.addObject("token","Token not found or expired");
        }

        return modelAndView;
    }

    @GetMapping({"/should/activate", "/should/activate/"})
    public ModelAndView shouldActivate(HttpServletRequest request){
        TodoUser user=userService.getCurrentUser();
        ModelAndView modelAndView=new ModelAndView();
        if(user.getStatus().equals("ACTIVATED")){
            modelAndView.setViewName("redirect:/tasks");

        }else {
            tokenService.sendToken(user, request);
            modelAndView.setViewName("should-activate");
        }
        return modelAndView;
    }


}

