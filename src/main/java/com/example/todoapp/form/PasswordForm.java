package com.example.todoapp.form;


import com.example.todoapp.validator.EqualField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


// i Will use bean validation in this form for difference
///TODO equal fields for confirming
@Data
@EqualField(field1 = "newPassword",field2 = "confirmPassword",message = "{confirmPassword.mustBe.same}")
public class PasswordForm {

    @NotBlank(message = "{password.required}")
    private String password;

    @NotBlank(message = "{password.required}")
    @Size(min = 8,message = "{password.length}")
    private String newPassword;

    @NotBlank(message = "{password.required}")
    @Size(min = 8,message = "{password.length}")
    private String confirmPassword;
}
