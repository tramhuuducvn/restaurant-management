package com.sdc.restaurantmanagement.exception;

import com.sdc.restaurantmanagement.payload.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;


@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);
    /**
     * Catch all type of exception.
     * @param exception Exception threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse handleAllException(Exception exception){
        exception.printStackTrace();
        return APIResponse.builder(HttpStatus.INTERNAL_SERVER_ERROR).message("An error has been occurred that is: " + exception.getMessage()).build();
    }

    /**
     * Catch all type of NoSuchElementException (ex: item doesn't exist).
     * @param exception Exception threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public APIResponse handleNoSuchElementException(NoSuchElementException exception){
        exception.printStackTrace();
        return APIResponse.builder(HttpStatus.NOT_FOUND).message("An error has been occurred that is: " + exception.getMessage()).build();
    }

}