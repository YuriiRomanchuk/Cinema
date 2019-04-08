package com.cinema.model.converter.EntityConverter;

import com.cinema.model.dto.RoomDto;
import com.cinema.model.entity.Room;

import java.text.ParseException;

public class RoomConverter implements EntityConverter<RoomDto, Room> {

    @Override
    public Room convert(RoomDto roomDto) throws ParseException {

        Room room = new Room();
        room.setName(roomDto.getName());
        return room;
    }
}
