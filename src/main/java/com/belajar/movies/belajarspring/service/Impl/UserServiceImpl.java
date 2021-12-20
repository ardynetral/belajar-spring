package com.belajar.movies.belajarspring.service.Impl;

import com.belajar.movies.belajarspring.controller.config.CustomException;
import com.belajar.movies.belajarspring.datasource.AppUserRole;
import com.belajar.movies.belajarspring.datasource.model.Users;
import com.belajar.movies.belajarspring.datasource.repository.UsersRepository;
import com.belajar.movies.belajarspring.security.JwtTokenProvider;
import com.belajar.movies.belajarspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public Map signup(Users users) {
        Map response = new HashMap();
        if (!userRepository.existsByUsername(users.getUsername())) {
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setEmail(users.getEmail());
            users.setUsername(users.getUsername());
            users.setCreatedAt(new Date());
            userRepository.save(users);
            response.put("name", users.getUsername());
            response.put("email", users.getEmail());
            response.put("token", jwtTokenProvider.createToken(users.getUsername(), users.getAppUserRoles()));
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @Override
    public Map login(String username, String password){
        Map response = new HashMap();
        try {

            Users user = userRepository.findByUsername(username);
            Map rest = new HashMap();
            rest.put("name", user.getUsername());
            rest.put("email", user.getEmail());
            rest.put("avatar", "");
            if (user.getIsAdmin()){
                rest.put("token", jwtTokenProvider.createToken(username, new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN))));
            }
            response.put("success", true);
            response.put("data", rest);
            response.put("message", "Success Login");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return response;
    }
}
