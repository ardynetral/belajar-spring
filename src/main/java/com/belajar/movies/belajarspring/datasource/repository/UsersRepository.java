package com.belajar.movies.belajarspring.datasource.repository;

import com.belajar.movies.belajarspring.datasource.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

}
