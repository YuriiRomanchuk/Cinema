package com.cinema.model.dao;

import com.cinema.model.entity.Room;
import com.cinema.model.entity.RoomPlace;

import java.util.List;

public class RoomPlaceDao implements GenericDao<RoomPlace> {

    private final DataSource dataSource;
    private DataSource.SqlFunction<RoomPlace> roomPlaceConverter;

    public RoomPlaceDao(DataSource dataSource) {
        this.dataSource = dataSource;
        receiveConverter();
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


    public List<RoomPlace> findAllbyRoomId(int room_id) {
        return dataSource.receiveRecords("SELECT places_id, row, place, room_id, rooms.name as room_name, " +
                        "rooms.name_english as rooms_name_english FROM(select id as places_id, row, place, room_id " +
                        "from places where room_id = ?) temp LEFT JOIN rooms ON temp.room_id = rooms.id",
                roomPlaceConverter,
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

    private void receiveConverter() {
        roomPlaceConverter = rs -> {
            RoomPlace roomPlace = new RoomPlace();
            Room room = new Room();
            room.setId(rs.getInt("room_id"));
            room.setName(rs.getString("room_name"));
            room.setNameEnglish(rs.getString("rooms_name_english"));
            roomPlace.setId(rs.getInt("places_id"));
            roomPlace.setRow(rs.getInt("row"));
            roomPlace.setPlace(rs.getInt("place"));
            roomPlace.setRoom(room);
            return roomPlace;
        };
    }
}
