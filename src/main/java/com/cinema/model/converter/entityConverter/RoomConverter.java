package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.entity.Room;

public class RoomConverter implements Converter<RoomDto, Room> {

    @Override
    public Room convert(RoomDto roomDto) {
        Room room = new Room();
        room.setName(roomDto.getName());
        return room;
    }
}
