package com.sdc.restaurantmanagement.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sdc.restaurantmanagement.payload.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;
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
        return APIResponse.builder(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
    }

    /**
     * Catch all type of NoSuchElementException (ex: item doesn't exist).
     * @param exception NoSuchElementException threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public APIResponse handleNoSuchElementException(NoSuchElementException exception){
        exception.printStackTrace();
        return APIResponse.builder(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
    }

    /**
     * Catch all type of InvalidFormatException (ex: param is invalid).
     * @param exception InvalidFormatException threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIResponse handleInvalidFormatException(InvalidFormatException exception){
        exception.printStackTrace();
        return APIResponse.builder(HttpStatus.BAD_REQUEST).message(exception.getMessage()).build();
    }

    /**
     * Catch all type of HttpMessageNotReadableException (ex: item doesn't exist).
     * @param exception HttpMessageNotReadableException threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        exception.printStackTrace();
        return APIResponse.builder(HttpStatus.BAD_REQUEST).message(exception.getMessage()).build();
    }


    /**
     * Catch all type of MalformedURLException (ex: invalid imageUrl).
     * @param exception MalformedURLException threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(MalformedURLException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIResponse handleMalformedURLException(MalformedURLException exception){
        exception.printStackTrace();
        return APIResponse.builder(HttpStatus.BAD_REQUEST).message(exception.getMessage()).build();
    }

}