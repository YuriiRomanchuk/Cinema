package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.TicketDto;
import com.cinema.service.RoomPlaceService;
import com.cinema.service.TicketService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class TicketController {

    private final TicketService ticketService;
    private final RoomPlaceService roomPlaceService;

    public TicketController(TicketService ticketService, RoomPlaceService roomPlaceService) {
        this.ticketService = ticketService;
        this.roomPlaceService = roomPlaceService;
    }

    public View buyTicket(TicketDto ticketDto) {
        View view;
        try {
            ticketService.buyTicket(ticketDto);
            view = new ViewModel("user-session-room");
            view.addParameter("filmSessionDto", ticketDto.getFilmSessionDto());
            view.addParameter("sessionDate", TimeConverter.changeStingDataToStingFormat(ticketDto.getFilmSessionDto().getDate(), "E MMM dd kk:mm:ss Z yyyy", "yyyy-MM-dd kk:mm:ss"));
            view.addParameter("purchasedSessionTicketsDto", ticketService.receivePurchasedSessionTickets(ticketDto.getFilmSessionDto().getId()));
            view.addParameter("roomPlacesDto", roomPlaceService.receiveRoomPlacesForRoom(ticketDto.getFilmSessionDto().getRoomDto().getId()));
        } catch (ServiceException e) {
            view = new ViewModel("user-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View showSessionRoom() {
        return new ViewModel("WEB-INF/jsp/admin/admin-session-room.jsp");
    }

    public View showUserSessionRoom() {
        return new ViewModel("WEB-INF/jsp/user/user-session-room.jsp");
    }

}
