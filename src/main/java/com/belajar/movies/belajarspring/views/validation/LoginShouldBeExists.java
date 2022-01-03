package com.belajar.movies.belajarspring.views.validation;

import com.belajar.movies.belajarspring.views.validation.impl.LoginValidationSouldhBeExists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LoginValidationSouldhBeExists.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginShouldBeExists {
    String message() default "Cannot Find username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
