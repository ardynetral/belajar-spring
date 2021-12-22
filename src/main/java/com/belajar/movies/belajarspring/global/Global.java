package com.belajar.movies.belajarspring.global;

import com.belajar.movies.belajarspring.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Global {

    public static String projectDir(){
        return System.getProperty("user.dir");
    }

    public static ResponseEntity<String> resSuccess(String res) {
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public static ResponseEntity<Response> resSuccess(Response res) {
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
