package com.belajar.movies.belajarspring.views.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ScheduleDto {

    @NotNull
    private Long movieId;
    @NotNull
    private Long studioId;
    @NotNull
    private String startTime;
    @NotNull
    private String endTime;

    private Double price;

    private String date;
}
