package com.belajar.movies.belajarspring.views.request;

import com.belajar.movies.belajarspring.views.validation.LoginShouldBeExists;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotNull
    @LoginShouldBeExists
    private String username;

    @NotNull
    private String password;
}
