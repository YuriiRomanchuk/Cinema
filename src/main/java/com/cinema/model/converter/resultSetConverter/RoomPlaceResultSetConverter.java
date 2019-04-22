package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.entity.RoomPlace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomPlaceResultSetConverter implements Converter<ResultSet, RoomPlace> {

    private static final Logger LOGGER = LogManager.getLogger(RoomPlaceResultSetConverter.class);
    private final RoomResultSetConverter roomResultSetConverter;

    public RoomPlaceResultSetConverter(RoomResultSetConverter roomResultSetConverter) {
        this.roomResultSetConverter = roomResultSetConverter;
    }

    @Override
    public RoomPlace convert(ResultSet resultSet) throws SQLException {
        RoomPlace roomPlace = new RoomPlace();
        roomPlace.setId(resultSet.getInt("place_id"));
        roomPlace.setRow(resultSet.getInt("place_row"));
        roomPlace.setPlace(resultSet.getInt("place_place"));
        roomPlace.setRoom(roomResultSetConverter.convert(resultSet));
        LOGGER.debug("Room place result set is converted!");
        return roomPlace;
    }
}
