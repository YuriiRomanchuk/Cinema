package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;

public class RoomPlaceConverter implements Converter<RoomPlaceDto, RoomPlace> {

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

        return roomPlace;
    }
}
