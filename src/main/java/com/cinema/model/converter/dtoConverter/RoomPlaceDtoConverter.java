package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.RoomDto;
import com.cinema.model.dto.RoomPlaceDto;

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

        RoomDto roomDto = roomDtoConverter.convertFromRoomPlaceRequest(request);

        int countAllPlaces = 1;
        for (int r = 1; r <= countOfRow && countAllPlaces < countOfPlaces; r++) {
            int countPlace = 0;
            for (int i = countAllPlaces; i <= countOfPlaces && countPlace < maxCountPlacesInRow; i++) {
                RoomPlaceDto roomPlaceDto = new RoomPlaceDto();
                roomPlaceDto.setPlace(i);
                roomPlaceDto.setRow(r);
                roomPlaceDto.setRoomDto(roomDto);
                roomPlacesDto.add(roomPlaceDto);
                countPlace++;
                countAllPlaces++;
            }
        }

        return roomPlacesDto;
    }

}
