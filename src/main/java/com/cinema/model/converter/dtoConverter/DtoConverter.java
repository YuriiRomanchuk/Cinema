package com.cinema.model.converter.dtoConverter;

import javax.servlet.http.HttpServletRequest;

public interface DtoConverter<V> {

    V convert(HttpServletRequest request);
}
