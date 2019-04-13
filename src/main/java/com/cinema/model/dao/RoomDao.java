package com.cinema.model.dao;

import com.cinema.model.converter.resultSetConverter.RoomResultSetConverter;
import com.cinema.model.entity.Room;

import java.util.List;

public class RoomDao implements GenericDao<Room> {

    private final DataSource dataSource;
    private final RoomResultSetConverter roomResultSetConverter;

    public RoomDao(DataSource dataSource, RoomResultSetConverter roomResultSetConverter) {
        this.dataSource = dataSource;
        this.roomResultSetConverter = roomResultSetConverter;
    }

    @Override
    public void insert(Room entity) {

        final String query = "insert into rooms (name, name_english) values(?,?)";

        dataSource.implementWrite(query, ps -> {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getNameEnglish());
        }, r -> entity.setId(r.getInt(1)));

    }

    @Override
    public Room findById(int id) {
        return null;
    }

    @Override
    public List<Room> findAll() {
        return dataSource.receiveRecords("select id as room_id, name as room_name, name_english as room_name_english from rooms",
                resultSet -> roomResultSetConverter.convert(resultSet),
                preparedStatement -> {
                });
    }

    @Override
    public void update(Room entity) {

    }

    @Override
    public void delete(int id) {

    }
}
