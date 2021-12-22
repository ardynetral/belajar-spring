package com.belajar.movies.belajarspring.controller;

import com.belajar.movies.belajarspring.global.Routes;
import com.belajar.movies.belajarspring.service.BackofficeService;
import com.belajar.movies.belajarspring.util.Response;
import com.belajar.movies.belajarspring.util.ResponseSuccess;
import com.belajar.movies.belajarspring.views.request.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.BACKOFFICE)
public class BackofficeController {

    @Autowired
    BackofficeService backofficeService;
    private ResponseSuccess responseSuccess = new ResponseSuccess();

    @PostMapping(Routes.MOVIES)
    public ResponseEntity<Response> create(HttpServletRequest request, @RequestBody ScheduleDto scheduleDto){

        var data = backofficeService.create(scheduleDto, request);
        responseSuccess.setMessage("Movie Created");

        return ResponseEntity.ok(responseSuccess);
    }
}
