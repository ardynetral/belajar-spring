package com.belajar.movies.belajarspring.datasource.repository;

import com.belajar.movies.belajarspring.datasource.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {
    Movies findAllById(Long id);
}
