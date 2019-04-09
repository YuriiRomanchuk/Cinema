package com.cinema.service;

import com.cinema.model.dao.RoomPlaceDao;

public class RoomPlaceService {

    private RoomPlaceDao roomPlaceDao;

    public RoomPlaceService(RoomPlaceDao roomPlaceDao) {
        this.roomPlaceDao = roomPlaceDao;
    }
}
