package com.belajar.movies.belajarspring.controller;

import com.belajar.movies.belajarspring.controller.config.RestExceptionHandler;
import com.belajar.movies.belajarspring.datasource.AppUserRole;
import com.belajar.movies.belajarspring.datasource.model.Users;
import com.belajar.movies.belajarspring.service.UserService;
import com.belajar.movies.belajarspring.util.IpAddressGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping(value = "/register")
    public ResponseEntity<Map> loginup(HttpServletRequest request,
                                       @RequestParam(value = "username", required = false) String username,
                                       @RequestParam(value = "password", required = false)String password,
                                       @RequestParam(value = "email", required = false)String email,
                                       @RequestParam(value = "roles", required = false)String roles,
                                       @RequestParam("file") MultipartFile file){

        log.info("IP Address : "+ IpAddressGet.getClientIp(request));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(uploadPath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Users users = new Users();
        users.setUsername(username);
        users.setEmail(email);
        users.setPassword(password);
        users.setAvatar(path.toString());
        if (roles.equalsIgnoreCase("ADMIN")){
            users.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN)));
            users.setIsAdmin(true);
        }
        Map response = userService.signup(users);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map> login(HttpServletRequest request,
                                     @RequestParam(value = "username", required = false)String username,
                                     @RequestParam(value = "password", required = false)String password){
        log.info("IP Address : "+ IpAddressGet.getClientIp(request));
        Map response = userService.login(username, password);
        return ResponseEntity.ok(response);

    }
}
