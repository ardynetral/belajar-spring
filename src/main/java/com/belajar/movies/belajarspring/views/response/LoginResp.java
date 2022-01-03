package com.belajar.movies.belajarspring.views.response;

import com.belajar.movies.belajarspring.datasource.model.Users;
import lombok.Data;

@Data
public class LoginResp {

    private String email;
    private String name;
    private String avatar;
    private String token;

    public static LoginResp mapping(Users users){
        var loginResp = new LoginResp();
        loginResp.setAvatar(users.getAvatar());
        loginResp.setEmail(users.getEmail());
        loginResp.setToken(users.getToken());
        loginResp.setName(users.getUsername());
        return loginResp;
    }
}
