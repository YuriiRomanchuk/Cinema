package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.TicketDtoConverter;
import com.cinema.model.dao.TicketDao;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.model.dto.TicketDto;
import com.cinema.model.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private final TicketDao ticketDao;
    private final TicketDtoConverter ticketDtoConverter;

    public TicketService(TicketDao ticketDao, TicketDtoConverter ticketDtoConverter) {
        this.ticketDao = ticketDao;
        this.ticketDtoConverter = ticketDtoConverter;
    }

    public List<RoomPlaceDto> receivePurchasedSessionTickets(int sessionId) throws ServiceException {
        try {
            List<RoomPlaceDto> roomPlacesDto = new ArrayList<>();
            List<TicketDto> ticketsDto = new ArrayList<>();
            List<Ticket> tickets = ticketDao.findTicketBySessionId(sessionId);
            tickets.forEach(roomPlace -> ticketsDto.add(ticketDtoConverter.convertFromTicketEntity(roomPlace)));
            ticketsDto.forEach(ticketDto -> roomPlacesDto.add(ticketDto.getRoomPlaceDto()));
            return roomPlacesDto;
        } catch (Exception e) {
            throw new ServiceException("Receive tickets dto failed", e);
        }
    }
}
