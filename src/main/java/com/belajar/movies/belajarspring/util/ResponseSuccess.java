package com.belajar.movies.belajarspring.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseSuccess implements Response{

    private Boolean success=true;
    private String message;
    private Object data;
}
