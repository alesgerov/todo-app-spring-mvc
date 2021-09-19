package com.example.todoapp.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualFieldValidator.class)
public @interface EqualField {
    String message() default "{confirmPassword.mustBe.same}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field1();

    String field2();
}
