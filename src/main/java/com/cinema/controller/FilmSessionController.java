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

import java.util.Date;

public class FilmSessionController {

    private final FilmSessionService filmSessionService;
    private final FilmService filmService;
    private final RoomService roomService;

    public FilmSessionController(FilmSessionService filmSessionService, FilmService filmService, RoomService roomService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
        this.roomService = roomService;
    }

    public View showFilmSessionPage() {
        View view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
        view.addParameter("currentDate", TimeConverter.changeStingDataToStingFormat(String.valueOf(new Date()), "E MMM dd kk:mm:ss Z yyyy", "yyyy-MM-dd"));
        return view;
    }


    public View addFilmSession(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
            filmSessionService.addFilmSession(filmSessionDto);
            showAdminFilmSessionPage(filmSessionDto, view);
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            /*             showAdminFilmSessionPage(filmSessionDto, view);*/
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
        return view;
    }


    public View showFilmSessionPageFilters(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
            showAdminFilmSessionPage(filmSessionDto, view);
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
         /*   view.addParameter("currentDate", filmSessionDto.getDate());
            view.addParameter("currentFilm_id", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
        */
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }

    private void showAdminFilmSessionPage(FilmSessionDto filmSessionDto, View view) throws ServiceException {
        view.addParameter("filmsSessionDto", filmSessionService.showFilmSessions(filmSessionDto));
        view.addParameter("filmsDto", filmService.receiveAllFilmsDto());
        view.addParameter("roomsDto", roomService.recieveAllRoomsDto());
        view.addParameter("filterDate", TimeConverter.changeStingDataToStingFormat(filmSessionDto.getDate(), "E MMM dd kk:mm:ss Z yyyy", "yyyy-MM-dd"));
        view.addParameter("filterFilmId", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
    }

}
