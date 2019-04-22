package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.entity.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoomConverter implements Converter<RoomDto, Room> {

    private static final Logger LOGGER = LogManager.getLogger(RoomConverter.class);

    @Override
    public Room convert(RoomDto roomDto) {
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setName(roomDto.getName());
        room.setNameEnglish(roomDto.getNameEnglish());
        LOGGER.debug("Room is converted from room dto!");
        return room;
    }

}
