package com.belajar.movies.belajarspring.service;

import com.belajar.movies.belajarspring.datasource.model.Movies;
import com.belajar.movies.belajarspring.views.request.MoviesDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface MoviesService {

    Movies create(MoviesDto moviesDto, HttpServletRequest req) throws IOException;
    Movies update(Long id, MoviesDto moviesDto, HttpServletRequest req) throws IOException;
}
