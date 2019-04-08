package com.cinema.model.converter.EntityConverter;

import java.text.ParseException;

public interface EntityConverter<T, V> {

    V convert(T dto) throws ParseException;
}
