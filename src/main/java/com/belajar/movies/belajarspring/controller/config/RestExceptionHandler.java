package com.belajar.movies.belajarspring.controller.config;

import com.belajar.movies.belajarspring.util.ErrorCode;
import com.belajar.movies.belajarspring.util.JsonUtil;
import com.belajar.movies.belajarspring.util.exception.BadRequestException;
import com.belajar.movies.belajarspring.util.exception.ForbiddenException;
import com.belajar.movies.belajarspring.util.exception.UnauthorizedException;
import com.belajar.movies.belajarspring.util.exception.ValidationException;
import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = { Exception.class})
    protected ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) {
        // do something with error message, save to log maybe? TODO
//        String bodyOfResponse = ErrorUtil.stackTraceToString(ex);
        ex.printStackTrace();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // send response
        Map response = new HashMap();
        
        if(ex instanceof BadRequestException) {
            response.put("error_code", ((BadRequestException) ex).getCode());
            response.put("error_message", ex.getMessage());

            return handleExceptionInternal(ex, JsonUtil.serialize(response),
                    headers, HttpStatus.BAD_REQUEST, request);
        }

        if(ex instanceof ValidationException) {
            Map errors = ((ValidationException) ex).getErrors();
            log.info("errors " + JsonUtil.serialize(errors));

            response.put("error_code", ex.getMessage());
            response.put("data", errors);

            return handleExceptionInternal(ex, JsonUtil.serialize(response),
                    headers, HttpStatus.BAD_REQUEST, request);
        }

        if(ex instanceof UnauthorizedException) {
            response.put("error_code", ErrorCode.ERROR.UNAUTHORIZED.name());
            response.put("error_message", ex.getMessage());
            return handleExceptionInternal(ex, JsonUtil.serialize(response),
                    headers, HttpStatus.UNAUTHORIZED, request);
        }

        if(ex instanceof ForbiddenException) {
            response.put("error_code", ErrorCode.ERROR.FORBIDDEN.name());
            response.put("error_message", ex.getMessage());
            return handleExceptionInternal(ex, JsonUtil.serialize(response),
                    headers, HttpStatus.FORBIDDEN, request);
        }

        if(ex instanceof NoSuchElementException) {
            response.put("error_code", ErrorCode.ERROR.DATA_NOT_FOUND.name());
            response.put("error_message", ErrorCode.ERROR.DATA_NOT_FOUND.name());
            return handleExceptionInternal(ex, JsonUtil.serialize(response),
                    headers, HttpStatus.NOT_FOUND, request);
        }

        // here is unexpected exception part
        try {
            Sentry.captureException(ex);
        } catch (Exception e) {
            // nothing do to here
        }

        if (ex.getMessage() != null && ex.getMessage().contains("org.hibernate.exception.JDBCConnectionException")) {
            response.put("error_code", ErrorCode.ERROR.DATABASE_TIMEOUT.name());
            response.put("error_message", ErrorCode.ERROR.DATABASE_TIMEOUT.getMessage());
            return handleExceptionInternal(ex, JsonUtil.serialize(response),
                    headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
        }

        response.put("error_message", ex.getMessage());
        response.put("error_code", ErrorCode.ERROR.INTERNAL_SERVER_ERROR.name());

        return handleExceptionInternal(ex, JsonUtil.serialize(response),
                headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
