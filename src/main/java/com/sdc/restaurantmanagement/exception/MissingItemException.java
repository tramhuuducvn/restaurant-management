package com.sdc.restaurantmanagement.exception;

public class MissingItemException extends RuntimeException {
    public MissingItemException() {

    }

    public MissingItemException(String mess) {
        super(mess);
    }
}
