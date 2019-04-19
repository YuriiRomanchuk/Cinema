package com.cinema.validator.typeValidator;

public interface Validator<T> {
    boolean validateValue(T value);
}
