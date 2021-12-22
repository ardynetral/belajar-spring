package com.belajar.movies.belajarspring.views.response;

import com.belajar.movies.belajarspring.datasource.model.Users;
import lombok.Data;

@Data
public class RegisterResp {

    private Long id;
    private String nama;
    private String email;
    private String avatar;
    private String token;

    public static RegisterResp mapping(Users users){
        var registtResp = new RegisterResp();
        registtResp.setId(users.getId());
        registtResp.setAvatar(users.getAvatar());
        registtResp.setEmail(users.getEmail());
        registtResp.setNama(users.getUsername());
        registtResp.setToken(users.getToken());
        return registtResp;
    }
}
