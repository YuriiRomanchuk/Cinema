package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.RoomPlaceDtoConverter;
import com.cinema.model.converter.entityConverter.RoomPlaceConverter;
import com.cinema.model.dao.RoomPlaceDao;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@RunWith(MockitoJUnitRunner.class)
public class RoomPlaceServiceTest {

    @Mock
    private RoomPlaceDao roomPlaceDao;

    @Mock
    private RoomPlaceConverter roomPlaceConverter;

    @Mock
    private RoomPlaceDtoConverter roomPlaceDtoConverter;

    @InjectMocks
    private RoomPlaceService roomPlaceService;

    @Test
    public void createRoomPlaceService() throws ServiceException {
        List<RoomPlaceDto> roomPlacesDto = new ArrayList<>();
        List<RoomPlace> roomPlaces = new ArrayList<>();

        RoomPlaceDto roomPlaceDto1 = new RoomPlaceDto();
        RoomPlaceDto roomPlaceDto2 = new RoomPlaceDto();

        RoomDto roomDto = new RoomDto();
        roomPlaceDto1.setRoomDto(roomDto);
        roomPlaceDto2.setRoomDto(roomDto);

        roomPlacesDto.add(roomPlaceDto1);
        roomPlacesDto.add(roomPlaceDto2);

        RoomPlace roomPlace1 = new RoomPlace();
        RoomPlace roomPlace2 = new RoomPlace();

        roomPlaces.add(roomPlace1);
        roomPlaces.add(roomPlace2);

        when(roomPlaceDao.findByRoomId(roomPlaceDto1.getId())).thenReturn(Optional.empty());
        when(roomPlaceConverter.convert(roomPlaceDto1)).thenReturn(roomPlace1);
        when(roomPlaceConverter.convert(roomPlaceDto2)).thenReturn(roomPlace2);

        roomPlaceService.createRoomPlaceService(roomPlacesDto);

        verify(roomPlaceConverter, atLeastOnce()).convert(roomPlaceDto1);
        verify(roomPlaceConverter, atLeastOnce()).convert(roomPlaceDto2);

        verify(roomPlaceDao).insertRoomPlaces(roomPlaces);
    }

    @Test
    public void receiveRoomPlacesForRoom() throws ServiceException {
        int roomId = 0;
        List<RoomPlaceDto> roomPlacesDto = new ArrayList<>();
        List<RoomPlace> roomPlaces = new ArrayList<>();

        RoomPlaceDto roomPlaceDto1 = new RoomPlaceDto();
        RoomPlaceDto roomPlaceDto2 = new RoomPlaceDto();

        roomPlacesDto.add(roomPlaceDto1);
        roomPlacesDto.add(roomPlaceDto2);

        RoomPlace roomPlace1 = new RoomPlace();
        RoomPlace roomPlace2 = new RoomPlace();

        roomPlaces.add(roomPlace1);
        roomPlaces.add(roomPlace2);

        when(roomPlaceDao.findAllByRoomId(roomId)).thenReturn(roomPlaces);
        when(roomPlaceDtoConverter.convertFromRoomPlaceEntity(roomPlace1)).thenReturn(roomPlaceDto1);
        when(roomPlaceDtoConverter.convertFromRoomPlaceEntity(roomPlace2)).thenReturn(roomPlaceDto2);

        List<RoomPlaceDto> roomPlaceDtos = roomPlaceService.receiveRoomPlacesForRoom(roomId);
        assertEquals(2, roomPlaceDtos.size());

        assertEquals(roomPlaceDto1, roomPlaceDtos.get(0));
        assertEquals(roomPlaceDto2, roomPlaceDtos.get(1));
    }
}