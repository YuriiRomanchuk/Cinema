package com.cinema.exception;

public class ServiceException extends Exception {

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(String s) {
        super(s);
    }
}
