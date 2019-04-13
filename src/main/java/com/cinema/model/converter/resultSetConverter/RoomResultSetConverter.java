package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomResultSetConverter implements Converter<ResultSet, Room> {

    @Override
    public Room convert(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setName(resultSet.getString("room_name"));
        room.setNameEnglish(resultSet.getString("room_name_english"));
        room.setId(resultSet.getInt("room_id"));
        return room;
    }
}
