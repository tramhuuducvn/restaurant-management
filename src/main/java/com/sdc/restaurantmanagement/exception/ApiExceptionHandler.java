package com.sdc.restaurantmanagement.exception;

import com.sdc.restaurantmanagement.payload.response.BodyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BodyResponse handleAllException(Exception exception, WebRequest request){
        return new BodyResponse.BodyResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).buildMessage("An error has been occurred that is: " + exception.getMessage()).build();
    }
}
