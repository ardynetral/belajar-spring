package com.belajar.movies.belajarspring.service;

import com.belajar.movies.belajarspring.datasource.model.Users;
import com.belajar.movies.belajarspring.views.request.LoginDto;
import com.belajar.movies.belajarspring.views.request.RegisterDto;

import java.io.IOException;

public interface UserService {

    Users signup(RegisterDto registerDto) throws IOException;
    Users login(LoginDto loginDto);

    Users detailUser(String username);
}
