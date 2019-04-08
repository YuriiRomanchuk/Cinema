package com.cinema.model.converter.dtoConverter;

import com.cinema.model.dto.RoomDto;

import javax.servlet.http.HttpServletRequest;

public class RoomDtoConverter implements DtoConverter<RoomDto> {
    @Override
    public RoomDto convert(HttpServletRequest request) {
        RoomDto roomDto = new RoomDto();
        roomDto.setName(request.getParameter("name"));
        return roomDto;
    }
}
