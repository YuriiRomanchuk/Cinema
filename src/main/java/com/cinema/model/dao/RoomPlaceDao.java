package com.cinema.model.dao;

import com.cinema.model.converter.resultSetConverter.RoomPlaceResultSetConverter;
import com.cinema.model.entity.RoomPlace;

import java.util.List;

public class RoomPlaceDao implements GenericDao<RoomPlace> {

    private final DataSource dataSource;
    private final RoomPlaceResultSetConverter roomPlaceResultSetConverter;

    public RoomPlaceDao(DataSource dataSource, RoomPlaceResultSetConverter roomPlaceResultSetConverter) {
        this.dataSource = dataSource;
        this.roomPlaceResultSetConverter = roomPlaceResultSetConverter;
    }

    @Override
    public void insert(RoomPlace entity) {

    }

    public void insertRoomPlaces(List<RoomPlace> roomPlaces) {

        final String query = "insert into places (row, place, room_id) values(?, ?, ?)";

        dataSource.implementWriteBatch(query, ps -> {
            for (RoomPlace roomPlace : roomPlaces) {
                ps.setInt(1, roomPlace.getRow());
                ps.setInt(2, roomPlace.getPlace());
                ps.setInt(3, roomPlace.getRoom().getId());
                ps.addBatch();
            }
        }, r -> {
        });
    }

    @Override
    public RoomPlace findById(int id) {
        return null;
    }


    public List<RoomPlace> findAllByRoomId(int room_id) {
        return dataSource.receiveRecords("SELECT place_id, place_row, place_place, room_id, rooms.name as room_name, " +
                        "rooms.name_english as room_name_english FROM(select id as place_id, row as place_row, place as place_place, room_id " +
                        "from places where room_id = ?) temp LEFT JOIN rooms ON temp.room_id = rooms.id",
                resultSet -> roomPlaceResultSetConverter.convert(resultSet),
                preparedStatement ->
                {
                    preparedStatement.setInt(1, room_id);
                });
    }

    @Override
    public List<RoomPlace> findAll() {
        return null;
    }

    @Override
    public void update(RoomPlace entity) {

    }

    @Override
    public void delete(int id) {

    }
}
