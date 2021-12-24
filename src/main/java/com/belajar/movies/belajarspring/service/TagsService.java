package com.belajar.movies.belajarspring.service;

import com.belajar.movies.belajarspring.datasource.model.Tags;
import com.belajar.movies.belajarspring.views.request.TagsDto;

import javax.servlet.http.HttpServletRequest;

public interface TagsService {

    Tags create(TagsDto tagsDto,  HttpServletRequest req);
}
