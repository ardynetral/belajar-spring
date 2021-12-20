package com.belajar.movies.belajarspring.util.exception;

import com.belajar.movies.belajarspring.util.ErrorCode;

public class BadRequestException extends DefaultException {
    private String code;


    public BadRequestException(ErrorCode.ERROR error) {
        super(error.getMessage());
        this.code = error.name();
    }

    public BadRequestException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
