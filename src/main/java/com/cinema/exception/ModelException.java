package com.cinema.exception;

public class ModelException extends Exception {

    public ModelException(String message, Exception e) {
        super(message, e);
    }
}
