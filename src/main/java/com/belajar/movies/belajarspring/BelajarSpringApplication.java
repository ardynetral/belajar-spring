package com.belajar.movies.belajarspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BelajarSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BelajarSpringApplication.class, args);
    }

}
