package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.TicketDto;
import com.cinema.model.entity.Ticket;

import javax.servlet.http.HttpServletRequest;

public class TicketDtoConverter implements Converter<HttpServletRequest, TicketDto> {

    private UserDtoConverter userDtoConverter;
    private RoomPlaceDtoConverter roomPlaceDtoConverter;
    private FilmSessionDtoConverter filmSessionDtoConverter;

    public TicketDtoConverter(UserDtoConverter userDtoConverter,
                              RoomPlaceDtoConverter roomPlaceDtoConverter,
                              FilmSessionDtoConverter filmSessionDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.roomPlaceDtoConverter = roomPlaceDtoConverter;
        this.filmSessionDtoConverter = filmSessionDtoConverter;
    }

    @Override
    public TicketDto convert(HttpServletRequest object) {
        return null;
    }

    public TicketDto convertFromTicketEntity(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setUserDto(userDtoConverter.convertFromUserEntity(ticket.getUser()));
        ticketDto.setRoomPlaceDto(roomPlaceDtoConverter.convertFromRoomPlaceEntity(ticket.getRoomPlace()));
        ticketDto.setFilmSessionDto(filmSessionDtoConverter.convertFromFilmEntity(ticket.getFilmSession()));
        return ticketDto;
    }


}
