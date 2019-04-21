package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.model.dto.TicketDto;
import com.cinema.service.FilmSessionService;
import com.cinema.service.RoomPlaceService;
import com.cinema.service.TicketService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class TicketController {

    private final TicketService ticketService;
    private final RoomPlaceService roomPlaceService;
    private final FilmSessionService filmSessionService;

    public TicketController(TicketService ticketService,
                            RoomPlaceService roomPlaceService,
                            FilmSessionService filmSessionService) {
        this.ticketService = ticketService;
        this.roomPlaceService = roomPlaceService;
        this.filmSessionService = filmSessionService;
    }

    public View buyTicket(TicketDto ticketDto) {
        View view;
        try {
            view = new ViewModel("user-session-room/"+ticketDto.getFilmSessionDto().getId());
            ticketService.buyTicket(ticketDto);
        } catch (ServiceException e) {
            view = new ViewModel("user-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View showAdminSessionRoom(int filmSessionId) {
        return showSessionRoom(filmSessionId, "WEB-INF/jsp/admin/admin-session-room.jsp", "admin-session-room");
    }

    public View showUserSessionRoom(int filmSessionId) {
        return showSessionRoom(filmSessionId, "WEB-INF/jsp/user/user-session-room.jsp", "user-personal-area");
    }

    public View showUnknownSessionRoom(int filmSessionId) {
        return showSessionRoom(filmSessionId, "WEB-INF/jsp/unknown/session-room.jsp", "index");
    }

    private View showSessionRoom(int filmSessionId, String path, String pathException) {
        View view;
        try {
            FilmSessionDto filmSessionDto = filmSessionService.receiveFilmSessionById(filmSessionId);
            view = new ViewModel(path);
            view.addParameter("filmSessionDto", filmSessionDto);
            view.addParameter("sessionDate", filmSessionDto.getDate());
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
