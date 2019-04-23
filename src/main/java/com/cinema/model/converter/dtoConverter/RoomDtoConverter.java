package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.entity.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RoomDtoConverter implements Converter<HttpServletRequest, RoomDto> {

    private static final Logger LOGGER = LogManager.getLogger(RoomDtoConverter.class);

    @Override
    public RoomDto convert(HttpServletRequest request) {
        RoomDto roomDto = new RoomDto();
        roomDto.setName(request.getParameter("name"));
        roomDto.setNameEnglish(request.getParameter("name_english"));
        LOGGER.debug("Room dto is converted from request!");
        return roomDto;
    }

    //TODO: refactoring to another class
    public RoomDto convertFromRoomEntity(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setNameEnglish(room.getNameEnglish());
        LOGGER.debug("Room dto is converted by entity!");
        return roomDto;
    }

    //TODO: refactoring to another class
    public RoomDto convertByRoomId(String room_id) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room_id == null ? 0 : Integer.valueOf(room_id));
        LOGGER.debug("Room dto is converted by room id!");
        return roomDto;
    }

}