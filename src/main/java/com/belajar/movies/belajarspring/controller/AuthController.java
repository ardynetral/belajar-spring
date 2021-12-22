package com.belajar.movies.belajarspring.controller;

import com.belajar.movies.belajarspring.global.Global;
import com.belajar.movies.belajarspring.global.Routes;
import com.belajar.movies.belajarspring.views.request.LoginDto;
import com.belajar.movies.belajarspring.views.request.RegisterDto;
import com.belajar.movies.belajarspring.views.response.LoginResp;
import com.belajar.movies.belajarspring.views.response.RegisterResp;
import com.belajar.movies.belajarspring.service.UserService;
import com.belajar.movies.belajarspring.util.IpAddressGet;
import com.belajar.movies.belajarspring.util.Response;
import com.belajar.movies.belajarspring.util.ResponseSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.AUTH)
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;
    private ResponseSuccess responseSuccess = new ResponseSuccess();

    @PostMapping(Routes.REGISTER)
    public ResponseEntity<Response> loginup(@ModelAttribute RegisterDto registerDto ) throws Exception {

        var data = userService.signup(registerDto);
        responseSuccess.setData(RegisterResp.mapping(data));
        responseSuccess.setMessage("Login Berhasil");
        return Global.resSuccess(responseSuccess);
    }

    @PostMapping(Routes.LOGIN)
    public ResponseEntity<Response> login(HttpServletRequest request, @RequestBody LoginDto loginDto){
        log.info("IP Address : "+ IpAddressGet.getClientIp(request));
        var data = userService.login(loginDto);
        responseSuccess.setData(LoginResp.mappring(data));
        responseSuccess.setMessage("Success Login");
        return ResponseEntity.ok(responseSuccess);

    }
}
