package com.belajar.movies.belajarspring.views.validation.impl;

import com.belajar.movies.belajarspring.service.UserService;
import com.belajar.movies.belajarspring.views.validation.LoginShouldBeExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidationSouldhBeExists implements ConstraintValidator<LoginShouldBeExists, String> {

    @Autowired
    UserService userService;

    @Override
    public void initialize(LoginShouldBeExists username) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if(username == null){
            return false;
        }
        return this.userService.detailUser(username) != null;
    }
}
