package com.belajar.movies.belajarspring.controller;

import com.belajar.movies.belajarspring.global.Routes;
import com.belajar.movies.belajarspring.service.TagsService;
import com.belajar.movies.belajarspring.util.Response;
import com.belajar.movies.belajarspring.util.ResponseSuccess;
import com.belajar.movies.belajarspring.views.request.TagsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1 + Routes.BACKOFFICE)
@Slf4j
public class TagsController {

    @Autowired
    TagsService tagsService;
    private ResponseSuccess resp = new  ResponseSuccess();

    @PostMapping(Routes.TAGS)
    public ResponseEntity<Response> create(HttpServletRequest req, @RequestBody TagsDto tagsDto){

        var data = tagsService.create(tagsDto, req);
        resp.setMessage("Tags Created");
        return ResponseEntity.ok(resp);
    }

}
