package com.cinema.model.converter.dtoConverter;

import com.cinema.model.dto.RoomDto;
import com.cinema.model.entity.Room;

import javax.servlet.http.HttpServletRequest;

public class RoomDtoConverter implements DtoConverter<RoomDto> {
    @Override
    public RoomDto convertFromHttpRequest(HttpServletRequest request) {
        RoomDto roomDto = new RoomDto();
        roomDto.setName(request.getParameter("name"));
        roomDto.setNameEnglish(request.getParameter("name_english"));
        return roomDto;
    }

    public RoomDto convertFromRoomEntity(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setNameEnglish(room.getNameEnglish());

        return roomDto;
    }
}
