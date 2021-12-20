package com.belajar.movies.belajarspring.datasource.repository;

import com.belajar.movies.belajarspring.datasource.model.MovieTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTagsRepository extends JpaRepository<MovieTags, Integer> {
}
