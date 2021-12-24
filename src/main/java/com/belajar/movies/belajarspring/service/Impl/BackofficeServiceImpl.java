package com.belajar.movies.belajarspring.service.Impl;

import com.belajar.movies.belajarspring.datasource.model.MovieSchedules;
import com.belajar.movies.belajarspring.datasource.repository.MovieSchedulesReposiory;
import com.belajar.movies.belajarspring.datasource.repository.UsersRepository;
import com.belajar.movies.belajarspring.security.JwtTokenProvider;
import com.belajar.movies.belajarspring.service.BackofficeService;
import com.belajar.movies.belajarspring.util.DateUtil;
import com.belajar.movies.belajarspring.views.request.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BackofficeServiceImpl implements BackofficeService {

    @Autowired
    MovieSchedulesReposiory movieSchedulesReposiory;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public MovieSchedules create(ScheduleDto  scheduleDto, HttpServletRequest req){
        usersRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        var schedule = new MovieSchedules();
        schedule.setMovieId(scheduleDto.getMovieId());
        schedule.setStudioId(scheduleDto.getStudioId());
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEndTime(scheduleDto.getEndTime());
        schedule.setPrice(scheduleDto.getPrice());
        schedule.setDate(DateUtil.convertStringToDate(scheduleDto.getDate()));

        return movieSchedulesReposiory.save(schedule);
    }


}
