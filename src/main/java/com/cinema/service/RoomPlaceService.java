package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.entityConverter.RoomPlaceConverter;
import com.cinema.model.dao.RoomPlaceDao;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;

import java.util.ArrayList;
import java.util.List;

public class RoomPlaceService {

    private final RoomPlaceDao roomPlaceDao;
    private final RoomPlaceConverter roomPlaceConverter;

    public RoomPlaceService(RoomPlaceDao roomPlaceDao, RoomPlaceConverter roomPlaceConverter) {
        this.roomPlaceDao = roomPlaceDao;
        this.roomPlaceConverter = roomPlaceConverter;
    }

    public void createRoomPlaceService(List<RoomPlaceDto> roomPlacesDto) throws ServiceException {
        try {
            List<RoomPlace> roomPlaces = new ArrayList<>();
            roomPlacesDto.forEach(f -> roomPlaces.add(roomPlaceConverter.convert(f)));
            roomPlaceDao.insertRoomPlaces(roomPlaces);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Create room places failed", e);
        }
    }

    public List<RoomPlaceDto> receiveRoomPlacesForRoom(int roomId) throws ServiceException {

        return null;

    }
}
