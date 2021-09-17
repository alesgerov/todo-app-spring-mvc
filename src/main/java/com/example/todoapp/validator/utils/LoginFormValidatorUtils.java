package com.example.todoapp.validator.utils;

import com.example.todoapp.form.LoginForm;
import com.example.todoapp.form.RegistrationForm;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class LoginFormValidatorUtils {

    public  void validateUsername(LoginForm form, Errors errors){
        if (!errors.hasFieldErrors("username") && form.getUsername()!=null && !GenericValidator.isInRange(form.getUsername().length(),FormConstants.MIN_LENGTH,FormConstants.MAX_LENGTH)){
            errors.rejectValue("username","username.length");
        }
        ///TODO detect duplicate username

    }

    public void validatePassword(LoginForm form, Errors errors) {
//        System.out.println(errors.getFieldErrorCount("password"));
//        errors.getFieldErrors("password").forEach(System.out::println);
        if (errors.getFieldErrorCount("password")==0 && form.getPassword()!=null && !GenericValidator.isInRange(form.getPassword().length(),8,100) || form.getPassword().equals("12345678"))
        {
            errors.rejectValue("password","password.normal");
        }
    }




}
