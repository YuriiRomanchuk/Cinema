package com.cinema.model.dao;

import com.cinema.model.entity.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomDao implements GenericDao<Room> {

    private DataSource dataSource;
    private DataSource.SqlFunction<Room> roomConverter;

    public RoomDao(DataSource dataSource) {
        this.dataSource = dataSource;
        receiveConverter();
    }

    @Override
    public void insert(Room entity) {

        final String query = "insert into rooms (name, name_english) values(?,?)";

        dataSource.implementWrite(query, ps -> {
            try {
                ps.setString(1, entity.getName());
                ps.setString(1, entity.getNameEnglish());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, r -> entity.setId(r.getInt(1)));

    }

    @Override
    public Room findById(int id) {
        return null;
    }

    @Override
    public List<Room> findAll() {
        return dataSource.receiveRecords("select id, name, name_english from rooms",
                roomConverter,
                preparedStatement -> {
                });
    }

    @Override
    public void update(Room entity) {

    }

    @Override
    public void delete(int id) {

    }


    private void receiveConverter() {
        roomConverter = rs -> {
            Room bank = new Room();
            bank.setName(rs.getString("name"));
            bank.setNameEnglish(rs.getString("name_english"));
            bank.setId(rs.getInt("id"));
            return bank;
        };

    }
}
