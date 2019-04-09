package com.cinema.model.dao;

import com.cinema.model.entity.RoomPlace;

import java.util.List;

public class RoomPlaceDao implements GenericDao<RoomPlace> {

    private DataSource dataSource;
    private DataSource.SqlFunction<RoomPlace> roomPlaceConverter;

    public RoomPlaceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(RoomPlace entity) {

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
