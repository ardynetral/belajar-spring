package com.belajar.movies.belajarspring.service.Impl;

import com.belajar.movies.belajarspring.datasource.model.MovieTags;
import com.belajar.movies.belajarspring.datasource.model.Movies;
import com.belajar.movies.belajarspring.datasource.repository.MovieTagsRepository;
import com.belajar.movies.belajarspring.datasource.repository.MoviesRepository;
import com.belajar.movies.belajarspring.datasource.repository.UsersRepository;
import com.belajar.movies.belajarspring.security.JwtTokenProvider;
import com.belajar.movies.belajarspring.service.MoviesService;
import com.belajar.movies.belajarspring.util.DateUtil;
import com.belajar.movies.belajarspring.util.UploadFile;
import com.belajar.movies.belajarspring.views.request.MoviesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

@Service("MoviesService")
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    MovieTagsRepository tagsRepository;

    @Value("${upload.path}")
    private String uploadPath;

    private UploadFile uploadFile = new UploadFile();

    @Override
    public Movies create(MoviesDto moviesDto, HttpServletRequest req) throws IOException {
        usersRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        Path file = uploadFile.upload(moviesDto.getPoster(), uploadPath);
        var movie = new Movies();
        movie.setOverview(moviesDto.getOverview());
        movie.setPoster(file.toString());
        movie.setTitle(moviesDto.getTitle());
        movie.setPlayUntil(DateUtil.convertStringToDate(moviesDto.getPlayUntil()));

        return moviesRepository.save(movie);
    }

    @Override
    public Movies update(Long id, MoviesDto moviesDto, HttpServletRequest req){
        usersRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        var movie = moviesRepository.findAllById(id);
        movie.setTitle(moviesDto.getTitle());
        movie.setOverview(moviesDto.getOverview());
        movie.setPlayUntil(DateUtil.convertStringToDate(moviesDto.getPlayUntil()));
        moviesRepository.save(movie);

        for (String tag : moviesDto.getTags()){
            var movieTag = new MovieTags();
            movieTag.setMovieId(movie.getId());
           if (!tag.equalsIgnoreCase("")){
               movieTag.setTagId(Integer.parseInt(tag));
           }
           tagsRepository.save(movieTag);
        }
        return movie;
    }

}
