package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoomPlaceConverter implements Converter<RoomPlaceDto, RoomPlace> {

    private static final Logger LOGGER = LogManager.getLogger(RoomPlaceConverter.class);
    private final RoomConverter roomConverter;

    public RoomPlaceConverter(RoomConverter roomConverter) {
        this.roomConverter = roomConverter;
    }

    @Override
    public RoomPlace convert(RoomPlaceDto roomPlaceDto) {

        RoomPlace roomPlace = new RoomPlace();
        roomPlace.setPlace(roomPlaceDto.getPlace());
        roomPlace.setRoom(roomConverter.convert(roomPlaceDto.getRoomDto()));
        roomPlace.setRow(roomPlaceDto.getRow());
        LOGGER.debug("Room place is converted from room place dto!");
        return roomPlace;
    }
}
