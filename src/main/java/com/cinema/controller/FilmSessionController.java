package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.FilmSessionDto;
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
            filmSessionService.showFilmSession(filmSessionDto);
            view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-session.jsp");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }
}
