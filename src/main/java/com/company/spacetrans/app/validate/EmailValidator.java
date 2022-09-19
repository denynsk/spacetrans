package com.company.spacetrans.app.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    private String email;

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
//        email = constraintAnnotation.email();
        email = "@";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null){
            return false;
        }

        return value.contains("@");
    }
}
