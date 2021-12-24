package com.belajar.movies.belajarspring.views.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MoviesDto {

    @NotNull
    private String title;
    private String overview;
    private MultipartFile poster;
    @NotNull
    private String playUntil;
    private List<String> tags;

}
