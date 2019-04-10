package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.entityConverter.RoomConverter;
import com.cinema.model.converter.dtoConverter.RoomDtoConverter;
import com.cinema.model.dao.RoomDao;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.entity.Room;

import java.util.ArrayList;
import java.util.List;

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
            e.printStackTrace();
            throw new ServiceException("Create room failed", e);
        }
    }

    public List<RoomDto> recieveAllRoomsDto() {

        List<RoomDto> roomsDto = new ArrayList<>();
        List<Room> rooms = roomDao.findAll();

        rooms.forEach(r -> roomsDto.add(roomDtoConverter.convertFromRoomEntity(r)));

        return roomsDto;
    }


}
