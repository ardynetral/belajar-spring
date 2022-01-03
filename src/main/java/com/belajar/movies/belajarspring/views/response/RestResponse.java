package com.belajar.movies.belajarspring.views.response;

import lombok.Data;

@Data
public class RestResponse {
    private String httpCode;
    private Object response;
}
