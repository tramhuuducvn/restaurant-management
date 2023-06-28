package com.sdc.restaurantmanagement.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sdc.restaurantmanagement.payload.APIResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(ApiExceptionHandler.class);

    /**
     * Common handle exception function.
     * 
     * @param exception exception.
     * @return Response detail error or exception occurred.
     */
    private APIResponse handleException(Exception exception) {
        exception.printStackTrace();
        LOGGER.error(exception.getMessage());
        return APIResponse.builder().message(exception.getMessage()).build();
    }

    /**
     * Catch all type of exception.
     * 
     * @param exception Exception threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse handleAllUncaughtException(Exception exception) {
        return this.handleException(exception);
    }

    /**
     * Catch all type of NoSuchElementException (ex: item doesn't exist).
     * 
     * @param exception NoSuchElementException threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public APIResponse handleNoSuchElementException(Exception exception) {
        return this.handleException(exception);
    }

    /**
     * Catch all type of InvalidFormatException (ex: param is invalid).
     * 
     * @param exception InvalidFormatException threw by some function.
     * @return ERROR Response to client.
     */
    @ExceptionHandler({ InvalidFormatException.class, HttpMessageNotReadableException.class,
            MalformedURLException.class, MissingItemException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIResponse handleInvalidFormatException(Exception exception) {
        return this.handleException(exception);
    }

    /**
     * Handle exception occur when add an item, but it already exists in database.
     * 
     * @param exception AlreadyExistException throw by some function.
     * @return ERROR response message.
     */
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public APIResponse handleItemAlreadyExistException(Exception exception) {
        return this.handleException(exception);
    }
}