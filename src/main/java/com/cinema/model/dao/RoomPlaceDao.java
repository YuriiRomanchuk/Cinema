package com.cinema.model.dao;

import com.cinema.model.entity.RoomPlace;

import java.util.List;

public class RoomPlaceDao implements GenericDao<RoomPlace> {

    private final DataSource dataSource;
    private DataSource.SqlFunction<RoomPlace> roomPlaceConverter;

    public RoomPlaceDao(DataSource dataSource) {
        this.dataSource = dataSource;
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
        }, r -> {});


    }

    @Override
    public RoomPlace findById(int id) {
        return null;
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
