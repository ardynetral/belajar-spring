package com.belajar.movies.belajarspring.service.Impl;

import com.belajar.movies.belajarspring.datasource.model.Tags;
import com.belajar.movies.belajarspring.datasource.repository.TagsRepository;
import com.belajar.movies.belajarspring.datasource.repository.UsersRepository;
import com.belajar.movies.belajarspring.security.JwtTokenProvider;
import com.belajar.movies.belajarspring.service.TagsService;
import com.belajar.movies.belajarspring.views.request.TagsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    TagsRepository tagsRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public Tags create(TagsDto tagsDto, HttpServletRequest req){
        usersRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        var tags = new Tags();
        tags.setName(tagsDto.getName());

        return tagsRepository.save(tags);
    }
}
