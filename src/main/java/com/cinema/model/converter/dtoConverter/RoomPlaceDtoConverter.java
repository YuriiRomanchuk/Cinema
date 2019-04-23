package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RoomPlaceDtoConverter implements Converter<HttpServletRequest, List<RoomPlaceDto>> {

    private static final Logger LOGGER = LogManager.getLogger(RoomPlaceDtoConverter.class);
    private final RoomDtoConverter roomDtoConverter;

    public RoomPlaceDtoConverter(RoomDtoConverter roomDtoConverter) {
        this.roomDtoConverter = roomDtoConverter;
    }

    @Override
    public List<RoomPlaceDto> convert(HttpServletRequest request) {

        List<RoomPlaceDto> roomPlacesDto = new ArrayList<>();
        int placesInRow = Integer.valueOf(request.getParameter("place"));
        int countOfRow = Integer.valueOf(request.getParameter("row"));

        RoomDto roomDto = roomDtoConverter.convertByRoomId(request.getParameter("room"));

        int countPlace = 1;
        for (int r = 1; r <= countOfRow; r++) {
            for (int i = 1; i <= placesInRow; i++) {
                RoomPlaceDto placeDto = new RoomPlaceDto();
                placeDto.setPlace(countPlace);
                placeDto.setRow(r);
                placeDto.setRoomDto(roomDto);
                roomPlacesDto.add(placeDto);
                countPlace++;
            }
        }

        LOGGER.debug("Room place dto list is converted from request!");
        return roomPlacesDto;
    }

    //TODO: refactoring to another class
    public RoomPlaceDto convertFromRoomPlaceEntity(RoomPlace roomPlace) {
        RoomPlaceDto placeDto = new RoomPlaceDto();
        placeDto.setId(roomPlace.getId());
        placeDto.setRow(roomPlace.getRow());
        placeDto.setPlace(roomPlace.getPlace());
        placeDto.setRoomDto(roomDtoConverter.convertFromRoomEntity(roomPlace.getRoom()));
        LOGGER.debug("Room place dto is converted from entity!");
        return placeDto;
    }

    //TODO: refactoring to another class
    public RoomPlaceDto convertFromTicketFields(String placeId, String placePlace, String placeRow, String sessionRoomId) {
        RoomPlaceDto placeDto = new RoomPlaceDto();
        placeDto.setId(Integer.valueOf(placeId));
        placeDto.setRow(Integer.valueOf(placeRow));
        placeDto.setPlace(Integer.valueOf(placePlace));
        placeDto.setRoomDto(roomDtoConverter.convertByRoomId(sessionRoomId));
        LOGGER.debug("Room place dto is converted from ticket fields!");
        return placeDto;
    }
}
