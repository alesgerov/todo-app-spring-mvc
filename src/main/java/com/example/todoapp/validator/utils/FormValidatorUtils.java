package com.example.todoapp.validator.utils;

import com.example.todoapp.form.RegistrationForm;
import com.example.todoapp.service.LoginService;
import com.example.todoapp.service.UserService;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class FormValidatorUtils {

    @Autowired
    private UserService userService;

    public  void validateUsername(RegistrationForm form, Errors errors){
        if (!errors.hasFieldErrors("username") && form.getUsername()!=null && !GenericValidator.isInRange(form.getUsername().length(),FormConstants.MIN_LENGTH,FormConstants.MAX_LENGTH)){
            errors.rejectValue("username","username.length");
        }
        if (userService.getUserByUsername(form.getUsername())!=null){
            errors.rejectValue("username","username.unique");
        }

    }




    public void validateEmail(RegistrationForm form, Errors errors){
        if (!errors.hasFieldErrors("email") &&form.getEmail()!=null && !GenericValidator.isEmail(form.getEmail())){
            errors.rejectValue("email","email.required");
        }
        if (userService.getUserByEmail(form.getEmail())!=null){
            errors.rejectValue("email","email.unique");
        }
    }



    public void validatePasswordConfirm(RegistrationForm form, Errors errors){
        if (!errors.hasFieldErrors("confirmPassword") && form.getPassword()!=null && form.getConfirmPassword()!=null &&
                !form.getPassword().equals(form.getConfirmPassword())
        ){
            errors.rejectValue("confirmPassword","confirmPassword.mustBe.same");
        }
    }


    public void validatePassword(RegistrationForm form, Errors errors) {
        if (errors.getFieldErrorCount("password")==0 && form.getPassword()!=null && !GenericValidator.isInRange(form.getPassword().length(),8,100) || form.getPassword().equals("12345678"))
        {
            errors.rejectValue("password","password.normal");
        }
    }


}
