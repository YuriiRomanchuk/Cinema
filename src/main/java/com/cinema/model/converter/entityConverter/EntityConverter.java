package com.cinema.model.converter.entityConverter;

public interface EntityConverter<T, V> {

    V convert(T dto);
}
