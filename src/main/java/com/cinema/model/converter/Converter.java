package com.cinema.model.converter;

import javax.servlet.http.HttpServletRequest;

public interface Converter<V> {

    V convertToObject(HttpServletRequest request);
}
