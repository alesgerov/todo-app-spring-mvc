package com.example.todoapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EqualFieldValidator implements ConstraintValidator<EqualField,Object> {
    private String field1;
    private String field2;
    @Override
    public void initialize(EqualField constraintAnnotation) {
        field1=constraintAnnotation.field1();
        field2=constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object fieldVal1=getFieldValue(o,field1);
            Object fieldVal2=getFieldValue(o,field2);
            return fieldVal1!=null && fieldVal1.equals(fieldVal2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Object getFieldValue(Object object ,String property) throws Exception{
        Class<?> class1=object.getClass();
        Field field=class1.getDeclaredField(property);
        field.setAccessible(true);
        return field.get(object);

    }
}
