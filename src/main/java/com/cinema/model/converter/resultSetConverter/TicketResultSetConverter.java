package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.entity.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketResultSetConverter implements Converter<ResultSet, Ticket> {

    private static final Logger LOGGER = LogManager.getLogger(TicketResultSetConverter.class);
    private final UserResultSetConverter userResultSetConverter;
    private final RoomPlaceResultSetConverter roomPlaceResultSetConverter;
    private final FilmSessionResultSetConverter filmSessionResultSetConverter;


    public TicketResultSetConverter(UserResultSetConverter userResultSetConverter,
                                    RoomPlaceResultSetConverter roomPlaceResultSetConverter,
                                    FilmSessionResultSetConverter filmSessionResultSetConverter) {
        this.userResultSetConverter = userResultSetConverter;
        this.roomPlaceResultSetConverter = roomPlaceResultSetConverter;
        this.filmSessionResultSetConverter = filmSessionResultSetConverter;
    }

    @Override
    public Ticket convert(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getInt("ticket_id"));
        ticket.setUser(userResultSetConverter.convert(resultSet));
        ticket.setRoomPlace(roomPlaceResultSetConverter.convert(resultSet));
        ticket.setFilmSession(filmSessionResultSetConverter.convert(resultSet));
        LOGGER.debug("Ticket result set is converted!");
        return ticket;
    }
}
