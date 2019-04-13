package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.entityConverter.FilmSessionConverter;
import com.cinema.model.dto.TicketDto;
import com.cinema.model.entity.Ticket;

import javax.servlet.http.HttpServletRequest;

public class TicketDtoConverter implements Converter<HttpServletRequest, TicketDto> {

    private UserDtoConverter userDtoConverter;
    private RoomDtoConverter roomDtoConverter;
    private FilmSessionConverter filmSessionConverter;

    public TicketDtoConverter(UserDtoConverter userDtoConverter,
                              RoomDtoConverter roomDtoConverter,
                              FilmSessionConverter filmSessionConverter) {
        this.userDtoConverter = userDtoConverter;
        this.roomDtoConverter = roomDtoConverter;
        this.filmSessionConverter = filmSessionConverter;
    }

    @Override
    public TicketDto convert(HttpServletRequest object) {
        return null;
    }

    public TicketDto convertFromTicketEntity(Ticket ticket) {

        TicketDto ticketDto = new TicketDto();
        return ticketDto;
    }


}
