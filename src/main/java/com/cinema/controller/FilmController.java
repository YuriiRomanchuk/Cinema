package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.FilmDto;
import com.cinema.service.FilmService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    public View createFilm(FilmDto filmDto) {
        View view;
        try {
            filmService.createFilm(filmDto);
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", "Film added!");
        } catch (ServiceException e) {
            view = new ViewModel("admin-add-film");
            view.addParameter("filmDto", filmDto);
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }


    public View showAddFilmPage() {
        return new ViewModel("WEB-INF/jsp/admin/admin-add-film.jsp");
    }

    public View showAllFilms() {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-show-all-films.jsp");
            view.addParameter("filmsDto", filmService.receiveAllFilmsDto());
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }

    }
}
