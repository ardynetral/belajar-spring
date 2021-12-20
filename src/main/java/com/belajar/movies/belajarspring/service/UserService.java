package com.belajar.movies.belajarspring.service;

import com.belajar.movies.belajarspring.datasource.model.Users;

import java.util.Map;

public interface UserService {

    Map signup(Users users);
    Map login(String username, String password);
}
