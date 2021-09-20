package com.example.todoapp.controller;


import com.example.todoapp.form.PasswordForm;
import com.example.todoapp.form.PasswordResetForm;
import com.example.todoapp.model.TodoUser;
import com.example.todoapp.model.UserToken;
import com.example.todoapp.service.PasswordService;
import com.example.todoapp.service.UserService;
import com.example.todoapp.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService tokenService;

    @Autowired
    private PasswordService passwordService;


    @GetMapping({"/password/change", "/password/change/"})
    public ModelAndView changePassword() {
        ModelAndView modelAndView = new ModelAndView();
        PasswordForm form = new PasswordForm();
        modelAndView.setViewName("change-password");
        modelAndView.addObject("form", form);
        return modelAndView;
    }

    @PostMapping({"/password/change", "/password/change/"})
    public ModelAndView changePasswordPost(@Valid @ModelAttribute(name = "form") PasswordForm form,
                                           BindingResult result,
                                           HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        TodoUser user = userService.getCurrentUser();
        boolean isEqual = passwordService.checkPassword(user.getPassword(), form.getPassword());
        if (isEqual && !result.hasErrors()) {
            user.setPassword(passwordService.hashPassword(form.getNewPassword()));
            userService.saveUser(user);
            modelAndView.setViewName("redirect:/");
        } else if (!isEqual) {
            result.rejectValue("password", "oldPassword.wrong");
            modelAndView.setViewName("change-password");
        } else {
            modelAndView.addObject("error", result.getAllErrors().get(0).getDefaultMessage());
            modelAndView.setViewName("change-password");
        }
        return modelAndView;
    }


    @GetMapping({"/forgot/email/verify/", "/forgot/email/verify"})
    public ModelAndView emailVerify() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.setViewName("forgot-password");
        return modelAndView;
    }

    @PostMapping({"/forgot/email/verify/", "/forgot/email/verify"})
    public ModelAndView emailVerifyPost(@RequestParam("email") String email, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<TodoUser> user = Optional.ofNullable(userService.getUserByEmail(email));
        if (user.isEmpty()) {
            modelAndView.addObject("emailNotFound", "Email not found");
            modelAndView.setViewName("forgot-password");
        } else {
            tokenService.sendTokenForForgot(user.get(), request);
            modelAndView.setViewName("mail-sent");
        }
        return modelAndView;
    }

    @GetMapping("/forgot/password")
    public ModelAndView resetPassword(@RequestParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("password-reset");
        ///TODO reset password
        Optional<UserToken> userToken = tokenService.getToken(token);
        if (userToken.isPresent()) {
            TodoUser user = userToken.get().getUser();
            if (tokenService.checkToken(token) && user.getStatus().equals("ACTIVATED")) {
                PasswordResetForm form = new PasswordResetForm();
                modelAndView.addObject("form", form);
            }
        } else {
            modelAndView.addObject("token", "Token not found or expired");
        }
        return modelAndView;
    }

    @PostMapping("/forgot/password")
    public ModelAndView resetPasswordPost(@RequestParam("token") String token, @Valid @ModelAttribute(name = "form") PasswordResetForm form,
                                          BindingResult result
    ) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("redirect:/" + token);
        } else {
            Optional<UserToken> userToken = tokenService.getToken(token);
            if (userToken.isPresent()) {
                TodoUser user=userToken.get().getUser();
                String pass = passwordService.hashPassword(form.getNewPassword());
                user.setPassword(pass);
                System.out.println(pass);
                System.out.println(form.getNewPassword());
                userService.saveUser(user);
                modelAndView.setViewName("redirect:/login");
            } else {
                modelAndView.addObject("token", "Token not found or expired");
            }
        }
        return modelAndView;
    }

}
