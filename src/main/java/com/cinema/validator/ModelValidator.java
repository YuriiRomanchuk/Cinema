package com.cinema.validator;

public interface ModelValidator<T, V> {

    V validate(T entity);

}
