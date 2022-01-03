package com.belajar.movies.belajarspring.views.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {

    @NotBlank
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Email(message = "email not valid")
    private String email;

    private MultipartFile avatar;


}
