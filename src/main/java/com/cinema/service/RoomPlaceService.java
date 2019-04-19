package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.RoomPlaceDtoConverter;
import com.cinema.model.converter.entityConverter.RoomPlaceConverter;
import com.cinema.model.dao.RoomPlaceDao;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;

import java.util.ArrayList;
import java.util.List;

public class RoomPlaceService {

    private final RoomPlaceDao roomPlaceDao;
    private final RoomPlaceConverter roomPlaceConverter;
    private final RoomPlaceDtoConverter roomPlaceDtoConverter;

    public RoomPlaceService(RoomPlaceDao roomPlaceDao,
                            RoomPlaceConverter roomPlaceConverter,
                            RoomPlaceDtoConverter roomPlaceDtoConverter) {
        this.roomPlaceDao = roomPlaceDao;
        this.roomPlaceConverter = roomPlaceConverter;
        this.roomPlaceDtoConverter = roomPlaceDtoConverter;
    }

    public void createRoomPlaceService(List<RoomPlaceDto> roomPlacesDto) throws ServiceException {
        try {
            List<RoomPlace> roomPlaces = new ArrayList<>();
            RoomPlace roomPlace = roomPlaceDao.findByRoomId(receiveRoomIdFromList(roomPlacesDto)).orElse(null);
            if (roomPlace == null) {
                roomPlacesDto.forEach(f -> roomPlaces.add(roomPlaceConverter.convert(f)));
                roomPlaceDao.insertRoomPlaces(roomPlaces);
            } else {
                throw new ServiceException("The places have already been distributed for this room.");
            }
        } catch (Exception e) {
            throw new ServiceException("Create room places failed", e);
        }
    }

    private int receiveRoomIdFromList(List<RoomPlaceDto> roomPlacesDto) {
        int roomId = -1;
        if (roomPlacesDto.size() > 0) {
            roomId = roomPlacesDto.get(0).getRoomDto().getId();
        }
        return roomId;
    }

    public List<RoomPlaceDto> receiveRoomPlacesForRoom(int roomId) throws ServiceException {
        try {
            List<RoomPlaceDto> roomPlacesDto = new ArrayList<>();
            List<RoomPlace> roomPlaces = roomPlaceDao.findAllByRoomId(roomId);
            roomPlaces.forEach(roomPlace -> roomPlacesDto.add(roomPlaceDtoConverter.convertFromRoomPlaceEntity(roomPlace)));
            return roomPlacesDto;
        } catch (Exception e) {
            throw new ServiceException("Create room places dto failed", e);
        }
    }
}
