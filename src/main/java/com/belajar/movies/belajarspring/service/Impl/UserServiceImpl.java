package com.belajar.movies.belajarspring.service.Impl;

import com.belajar.movies.belajarspring.datasource.AppUserRole;
import com.belajar.movies.belajarspring.datasource.model.Users;
import com.belajar.movies.belajarspring.datasource.repository.UsersRepository;
import com.belajar.movies.belajarspring.global.UploadDataType;
import com.belajar.movies.belajarspring.views.request.LoginDto;
import com.belajar.movies.belajarspring.views.request.RegisterDto;
import com.belajar.movies.belajarspring.security.JwtTokenProvider;
import com.belajar.movies.belajarspring.service.UserService;
import com.belajar.movies.belajarspring.util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    private UploadFile uploadFile = new UploadFile();

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public Users signup(RegisterDto registerDto) throws IOException {
        Path file = uploadFile.upload(registerDto.getAvatar(), uploadPath);
        var uploadType = UploadDataType.valueOf("IMAGE");
        switch (uploadType) {
            case IMAGE:
                var extension = FilenameUtils.getExtension(registerDto.getAvatar().getOriginalFilename());
                uploadFile.compressImage(file, extension, 30, uploadPath);
            case CSV:
            case DOC:
            case DOCX:
            case PDF:
        }

        var neUser = new Users();
        neUser.setUsername(registerDto.getName());
        neUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        neUser.setEmail(registerDto.getEmail());
        neUser.setCreatedAt(new Date());
        neUser.setAvatar(file.toString());
        neUser.setIsAdmin(true);
        neUser.setToken(jwtTokenProvider.createToken(registerDto.getName(), new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN))));

        return userRepository.save(neUser);
    }

    @Override
    public Users login(LoginDto loginDto){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        Users user = userRepository.findByUsername(loginDto.getUsername());
        user.setToken(jwtTokenProvider.createToken(loginDto.getUsername(), new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN))));
        return user;
    }
}
