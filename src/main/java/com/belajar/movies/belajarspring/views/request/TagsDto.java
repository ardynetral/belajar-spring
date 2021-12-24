package com.belajar.movies.belajarspring.views.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TagsDto {

    @NotNull
    private String name;
}
