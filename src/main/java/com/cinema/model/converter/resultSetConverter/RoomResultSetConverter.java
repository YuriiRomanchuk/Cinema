package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.entity.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomResultSetConverter implements Converter<ResultSet, Room> {

    private static final Logger LOGGER = LogManager.getLogger(RoomResultSetConverter.class);

    @Override
    public Room convert(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setName(resultSet.getString("room_name"));
        room.setNameEnglish(resultSet.getString("room_name_english"));
        room.setId(resultSet.getInt("room_id"));
        LOGGER.debug("Room result set is converted!");
        return room;
    }
}
