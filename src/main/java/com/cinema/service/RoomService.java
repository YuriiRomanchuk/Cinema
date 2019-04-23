package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.RoomDtoConverter;
import com.cinema.model.converter.entityConverter.RoomConverter;
import com.cinema.model.dao.RoomDao;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomService {

    private final RoomDao roomDao;
    private final RoomConverter roomConverter;
    private final RoomDtoConverter roomDtoConverter;

    public RoomService(RoomDao roomDao, RoomConverter roomConverter, RoomDtoConverter roomDtoConverter) {
        this.roomDao = roomDao;
        this.roomConverter = roomConverter;
        this.roomDtoConverter = roomDtoConverter;
    }

    public void createRoom(RoomDto roomDto) throws ServiceException {
        try {
            Room room = roomConverter.convert(roomDto);
            roomDao.insert(room);
        } catch (Exception e) {
            throw new ServiceException("Create room failed", e);
        }
    }

    public List<RoomDto> receiveAllRoomsDto() throws ServiceException {
        try {
            return roomDao.findAll().stream().map(roomDtoConverter::convertFromRoomEntity).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Create room dto failed", e);
        }
    }


}
