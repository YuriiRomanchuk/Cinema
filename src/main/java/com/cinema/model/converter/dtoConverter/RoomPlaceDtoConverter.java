package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomPlaceDto;

import javax.servlet.http.HttpServletRequest;

public class RoomPlaceDtoConverter implements Converter<HttpServletRequest, RoomPlaceDto> {

    @Override
    public RoomPlaceDto convert(HttpServletRequest request) {
        return null;
    }
}
