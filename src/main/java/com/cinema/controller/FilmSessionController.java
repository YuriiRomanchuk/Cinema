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
            view = new ViewModel("admin-session");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View showFilmSessionPageFiltersAdmin(FilmSessionDto filmSessionDto) {
        return showFilmSessionPageFilters(filmSessionDto, "WEB-INF/jsp/admin/admin-session.jsp", "admin-session");
    }

    public View showFilmSessionPageFiltersUser(FilmSessionDto filmSessionDto) {
        return showFilmSessionPageFilters(filmSessionDto, "WEB-INF/jsp/user/user-session.jsp", "user-personal-area");
    }

    public View showFilmSessionPageFiltersUnknown(FilmSessionDto filmSessionDto) {
        return showFilmSessionPageFilters(filmSessionDto, "WEB-INF/jsp/index.jsp", "index");
    }

    public View showFilmSessionPageFilters(FilmSessionDto filmSessionDto, String path, String pathException) {
        View view;
        try {
            view = new ViewModel(path);
            showAdminFilmSessionPage(filmSessionDto, view);
            view.addParameter("filterFilmId", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
            return view;
        } catch (ServiceException e) {
            view = new ViewModel(pathException);
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

    public View showSessionRoomAdmin(int filmSessionId) {
        return new RedirectViewModel(new ViewModel("admin-session-room/" + filmSessionId));
    }

    public View showSessionRoomUser(int filmSessionId) {
        return new RedirectViewModel(new ViewModel("user-session-room/" + filmSessionId));
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

    public View showAdminFilmsSale(Date filterDate) {
        View view = new ViewModel("admin-personal-area");
        view.addParameter("filterDate", filterDate);
        return new RedirectViewModel(view);
    }
}
