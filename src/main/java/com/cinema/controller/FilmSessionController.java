package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.service.*;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

import java.util.Date;

public class FilmSessionController {

    private final FilmSessionService filmSessionService;
    private final FilmService filmService;
    private final RoomService roomService;
    private final TicketService ticketService;
    private final RoomPlaceService roomPlaceService;

    public FilmSessionController(FilmSessionService filmSessionService,
                                 FilmService filmService,
                                 RoomService roomService,
                                 TicketService ticketService,
                                 RoomPlaceService roomPlaceService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
        this.roomService = roomService;
        this.ticketService = ticketService;
        this.roomPlaceService = roomPlaceService;
    }

    public View addFilmSession(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("admin-session");
            filmSessionService.addFilmSession(filmSessionDto);
            showAdminFilmSessionPage(filmSessionDto, view);
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }


    public View showFilmSessionPageFilters(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
            showAdminFilmSessionPage(filmSessionDto, view);
            view.addParameter("filterFilmId", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }

    public View deleteFilmSession(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("admin-session");
            filmSessionService.deleteFilmSession(filmSessionDto);
            showAdminFilmSessionPage(filmSessionDto, view);
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View showSessionRoom(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("admin-session-room");
            view.addParameter("filmSessionDto", filmSessionDto);
            view.addParameter("sessionDate", TimeConverter.changeStingDataToStingFormat(filmSessionDto.getDate(), "E MMM dd kk:mm:ss Z yyyy", "yyyy-MM-dd"));
            view.addParameter("purchasedSessionTicketsDto", ticketService.receivePurchasedSessionTickets(filmSessionDto.getId()));
            view.addParameter("roomPlacesDto", roomPlaceService.receiveRoomPlacesForRoom(filmSessionDto.getRoomDto().getId()));
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    private void showAdminFilmSessionPage(FilmSessionDto filmSessionDto, View view) throws ServiceException {
        view.addParameter("filmsSessionDto", filmSessionService.showFilmSessions(filmSessionDto));
        view.addParameter("filmsDto", filmService.receiveAllFilmsDto());
        view.addParameter("roomsDto", roomService.receiveAllRoomsDto());
        view.addParameter("filterDate", TimeConverter.changeStingDataToStingFormat(filmSessionDto.getDate(), "E MMM dd kk:mm:ss Z yyyy", "yyyy-MM-dd"));

        Date sessionDate = TimeConverter.convertStringToDate(filmSessionDto.getDate(), "E MMM dd kk:mm:ss Z yyyy");
        Date endOfTodayDate = TimeConverter.receiveEndOfDay(new Date());
        view.addParameter("isLastDay", sessionDate.before(endOfTodayDate));
    }

}
