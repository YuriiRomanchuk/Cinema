package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.RoomPlaceDtoConverter;
import com.cinema.model.converter.entityConverter.RoomPlaceConverter;
import com.cinema.model.dao.RoomPlaceDao;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;

import java.util.List;
import java.util.stream.Collectors;

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
            if (!roomPlaceDao.findByRoomId(receiveRoomIdFromList(roomPlacesDto)).isPresent()) {
                List<RoomPlace> roomPlaces = roomPlacesDto.stream().map(roomPlaceConverter::convert).collect(Collectors.toList());
                roomPlaceDao.insertRoomPlaces(roomPlaces);
            } else {
                throw new ServiceException("The places have already been distributed for this room.");
            }
        } catch (Exception e) {
            throw new ServiceException("Create room places failed", e);
        }
    }

    private int receiveRoomIdFromList(List<RoomPlaceDto> roomPlacesDto) {
        return roomPlacesDto.stream().map(p -> p.getRoomDto().getId()).findFirst().orElse(-1);
    }

    public List<RoomPlaceDto> receiveRoomPlacesForRoom(int roomId) throws ServiceException {
        try {
            List<RoomPlace> roomPlaces = roomPlaceDao.findAllByRoomId(roomId);
            return roomPlaces.stream().map(roomPlaceDtoConverter::convertFromRoomPlaceEntity).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Create room places dto failed", e);
        }
    }
}
