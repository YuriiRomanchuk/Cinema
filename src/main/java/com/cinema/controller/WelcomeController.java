package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.service.FilmService;
import com.cinema.service.FilmSessionService;
import com.cinema.service.RoomService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class WelcomeController {

    private static final Logger LOGGER = LogManager.getLogger(WelcomeController.class);
    private final FilmService filmService;
    private final RoomService roomService;
    private final FilmSessionService filmSessionService;

    public WelcomeController(FilmService filmService, RoomService roomService, FilmSessionService filmSessionService) {
        this.filmService = filmService;
        this.roomService = roomService;
        this.filmSessionService = filmSessionService;
    }

    public View showIndexPage(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/index.jsp");
            view.addParameter("filmsSessionDto", filmSessionService.showFilmSessions(filmSessionDto));
            view.addParameter("filmsDto", filmService.receiveAllFilmsDto());
            view.addParameter("roomsDto", roomService.receiveAllRoomsDto());
            view.addParameter("filterDate", TimeConverter.changeDataToStringFormat(new Date(), "yyyy-MM-dd "));
            LOGGER.debug("Index page show");
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("error");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("Page is not shown!" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View showIndexPageRedirect() {
        return new RedirectViewModel(new ViewModel("index"));
    }

    public View showSessionRoom(int filmSessionId) {
        return new RedirectViewModel(new ViewModel("unknown-session-room/" + filmSessionId));
    }
}
