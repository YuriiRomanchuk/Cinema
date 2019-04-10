package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.service.FilmSessionService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    public View showFilmSessionPage() {
        return new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
    }

    public View showFilmSessionPageFilters(FilmSessionDto filmSessionDto) {

        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
            view.addParameter("filmsSessionDto", filmSessionService.showFilmSession(filmSessionDto));
            view.addParameter("currentDate", filmSessionDto.getDate());
            view.addParameter("currentFilm_id", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-session.jsp");
            view.addParameter("currentDate", filmSessionDto.getDate());
            view.addParameter("currentFilm_id", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }
}
