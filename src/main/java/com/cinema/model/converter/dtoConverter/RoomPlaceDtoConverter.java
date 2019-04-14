package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.entity.RoomPlace;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RoomPlaceDtoConverter implements Converter<HttpServletRequest, List<RoomPlaceDto>> {

    private final RoomDtoConverter roomDtoConverter;

    public RoomPlaceDtoConverter(RoomDtoConverter roomDtoConverter) {
        this.roomDtoConverter = roomDtoConverter;
    }

    @Override
    public List<RoomPlaceDto> convert(HttpServletRequest request) {

        List<RoomPlaceDto> roomPlacesDto = new ArrayList<>();
        int countOfPlaces = Integer.valueOf(request.getParameter("place"));
        int placesInRow = Integer.valueOf(request.getParameter("placesInRow"));
        int maxCountPlacesInRow = countOfPlaces <= placesInRow ? countOfPlaces : placesInRow;
        int countOfRow = countOfPlaces <= placesInRow ? 1 : Integer.valueOf(request.getParameter("row"));

        RoomDto roomDto = roomDtoConverter.convertForRoomId(request.getParameter("room"));

        int countAllPlaces = 1;
        for (int r = 1; r <= countOfRow && countAllPlaces < countOfPlaces; r++) {
            int countPlace = 0;
            for (int i = countAllPlaces; i <= countOfPlaces && countPlace < maxCountPlacesInRow; i++) {
                RoomPlaceDto placeDto = new RoomPlaceDto();
                placeDto.setPlace(i);
                placeDto.setRow(r);
                placeDto.setRoomDto(roomDto);
                roomPlacesDto.add(placeDto);
                countPlace++;
                countAllPlaces++;
            }
        }

        return roomPlacesDto;
    }

    public RoomPlaceDto convertFromRoomPlaceEntity(RoomPlace roomPlace) {
        RoomPlaceDto placeDto = new RoomPlaceDto();
        placeDto.setId(roomPlace.getId());
        placeDto.setRow(roomPlace.getRow());
        placeDto.setPlace(roomPlace.getPlace());
        placeDto.setRoomDto(roomDtoConverter.convertFromRoomEntity(roomPlace.getRoom()));
        return placeDto;
    }

    public RoomPlaceDto convertFromTicketFields(String placeId, String placePlace, String placeRow, String sessionRoomId) {
        RoomPlaceDto placeDto = new RoomPlaceDto();
        placeDto.setId(Integer.valueOf(placeId));
        placeDto.setRow(Integer.valueOf(placeRow));
        placeDto.setPlace(Integer.valueOf(placePlace));
        placeDto.setRoomDto(roomDtoConverter.convertForRoomId(sessionRoomId));
        return placeDto;
    }
}
