package com.belajar.movies.belajarspring.controller;

import com.belajar.movies.belajarspring.global.Global;
import com.belajar.movies.belajarspring.global.Routes;
import com.belajar.movies.belajarspring.service.MoviesService;
import com.belajar.movies.belajarspring.util.Response;
import com.belajar.movies.belajarspring.util.ResponseSuccess;
import com.belajar.movies.belajarspring.views.request.MoviesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.MOVIES)
public class MoviesController {

    @Autowired
    MoviesService moviesService;
    private ResponseSuccess responseSuccess = new ResponseSuccess();

    @RequestMapping
    public ResponseEntity<Response> create(HttpServletRequest req, @ModelAttribute MoviesDto moviesDto) throws Exception {
        var data = moviesService.create(moviesDto, req);
        responseSuccess.setMessage("Success Created");
        return ResponseEntity.ok(responseSuccess);
    }

    @PutMapping(path = Routes.ID)
    public ResponseEntity<String> update(HttpServletRequest req,
                                         @PathVariable(name = "id") Long id,
                                         @Valid @ModelAttribute MoviesDto moviesDto,
                                         @ModelAttribute(name = "tag[0]") String tags,
                                         @ModelAttribute(name = "tag[1]") String tags1,
                                         @ModelAttribute(name = "tag[2]") String tags2) throws IOException {
        List<String> str = new ArrayList<String>();
        str.add(tags);
        str.add(tags1);
        str.add(tags2);
        moviesDto.setTags(str);
        var data = moviesService.update(id, moviesDto, req);
        return Global.resSuccess("Updated Success");
    }

}
