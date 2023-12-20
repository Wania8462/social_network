package com.example.social_network.exception.handler;

import com.example.social_network.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    private ResponseEntity<ErrorObject> handle(PostNotFoundException ex,
                                               WebRequest request) {
        return mapError(ex, request, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorObject> mapError(RuntimeException ex,
                                                 WebRequest request,
                                                 HttpStatus httpStatus) {
        ErrorObject errorObject = ErrorObject.builder()
                .timeStamp(new Date())
                .status(httpStatus.value())
                .error(ex.getMessage())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorObject, httpStatus);
    }
}
