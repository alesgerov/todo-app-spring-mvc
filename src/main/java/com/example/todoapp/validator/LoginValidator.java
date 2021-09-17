package com.example.todoapp.validator;

import com.example.todoapp.form.LoginForm;
import com.example.todoapp.validator.utils.FormValidatorUtils;
import com.example.todoapp.validator.utils.LoginFormValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Autowired
    private LoginFormValidatorUtils utils;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(LoginForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginForm form=(LoginForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.required");
        utils.validateUsername(form,errors);
        utils.validatePassword(form,errors);
    }
}
