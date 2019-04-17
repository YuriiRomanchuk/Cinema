package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmSessionDto;
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
            view = new ViewModel("user-session-room");
            ticketService.buyTicket(ticketDto);
        } catch (ServiceException e) {
            view = new ViewModel("user-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View showAdminSessionRoom(FilmSessionDto filmSessionDto) {
        return showSessionRoom(filmSessionDto, "WEB-INF/jsp/admin/admin-session-room.jsp", "admin-session-room");
    }

    public View showUserSessionRoom(FilmSessionDto filmSessionDto) {
        return showSessionRoom(filmSessionDto, "WEB-INF/jsp/user/user-session-room.jsp", "user-personal-area");
    }

    private View showSessionRoom(FilmSessionDto filmSessionDto, String path, String pathException) {
        View view;
        try {
            view = new ViewModel(path);
            view.addParameter("filmSessionDto", filmSessionDto);
            view.addParameter("sessionDate", TimeConverter.changeStingDataToStingFormat(filmSessionDto.getDate(), "E MMM dd kk:mm:ss Z yyyy", "yyyy-MM-dd kk:mm:ss"));
            view.addParameter("purchasedSessionTicketsDto", ticketService.receivePurchasedSessionTickets(filmSessionDto.getId()));
            view.addParameter("roomPlacesDto", roomPlaceService.receiveRoomPlacesForRoom(filmSessionDto.getRoomDto().getId()));
            return view;
        } catch (ServiceException e) {
            view = new ViewModel(pathException);
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }
}
