package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.dtoConverter.user.UserDtoConverter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.TicketDto;
import com.cinema.model.entity.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class TicketDtoConverter implements Converter<HttpServletRequest, TicketDto> {

    private static final Logger LOGGER = LogManager.getLogger(TicketDtoConverter.class);
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
    public TicketDto convert(HttpServletRequest request) {

        String numberOfLine = request.getParameter("buy-place");
        String[] place = numberOfLine.split("_");

        String placeId = place[0];
        String placePlace = place[1];
        String placeRow = place[2];
        String sessionId = place[3];
        String sessionFilm = place[4];
        String sessionRoom = place[5];
        String sessionDate =  String.valueOf(TimeConverter.convertStringToDate(place[6], "yyyy-MM-dd kk:mm:ss"));

        TicketDto ticketDto = new TicketDto();
        ticketDto.setFilmSessionDto(filmSessionDtoConverter.convertFilmSessionByIdes(sessionId,sessionFilm, sessionRoom, sessionDate));
        ticketDto.setRoomPlaceDto(roomPlaceDtoConverter.convertFromTicketFields(placeId, placePlace, placeRow, sessionRoom));
        ticketDto.setUserDto(userDtoConverter.convertForUserId((Integer) request.getSession().getAttribute("user_id")));

        request.getSession().setAttribute("filmSessionDto", ticketDto.getFilmSessionDto());
        LOGGER.debug("Ticket dto is converted from request!");
        return ticketDto;
    }

    public TicketDto convertFromTicketEntity(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setUserDto(userDtoConverter.convertFromUserEntity(ticket.getUser()));
        ticketDto.setRoomPlaceDto(roomPlaceDtoConverter.convertFromRoomPlaceEntity(ticket.getRoomPlace()));
        ticketDto.setFilmSessionDto(filmSessionDtoConverter.convertFromFilmEntity(ticket.getFilmSession()));
        LOGGER.debug("Ticket dto is converted from entity!");
        return ticketDto;
    }


}
