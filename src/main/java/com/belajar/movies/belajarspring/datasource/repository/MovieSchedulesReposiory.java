package com.belajar.movies.belajarspring.datasource.repository;

import com.belajar.movies.belajarspring.datasource.model.MovieSchedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSchedulesReposiory extends JpaRepository<MovieSchedules, Integer> {
}
