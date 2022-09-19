package com.company.spacetrans.app.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EmailValidation {
    String message() default "Некорректный емайл {email}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String email() default "@";
}
