package com.example.todoapp.validator;


import com.example.todoapp.form.RegistrationForm;
import com.example.todoapp.validator.utils.FormValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

    @Autowired
    private FormValidatorUtils utils;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(RegistrationForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationForm form=(RegistrationForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"confirmPassword","confirmPassword.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","email.required");

        utils.validateUsername(form,errors);
        utils.validateEmail(form,errors);
        utils.validatePassword(form,errors);
        utils.validatePasswordConfirm(form,errors);
    }
}
