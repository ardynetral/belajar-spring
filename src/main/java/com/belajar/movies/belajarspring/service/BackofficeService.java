package com.belajar.movies.belajarspring.service;

import com.belajar.movies.belajarspring.datasource.model.MovieSchedules;
import com.belajar.movies.belajarspring.views.request.ScheduleDto;

import javax.servlet.http.HttpServletRequest;

public interface BackofficeService {

    MovieSchedules create(ScheduleDto scheduleDto, HttpServletRequest httpServletRequest);
}
