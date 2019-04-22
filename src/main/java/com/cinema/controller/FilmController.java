package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.FilmDto;
import com.cinema.service.FilmService;
import com.cinema.validator.AddFilmValidator;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilmController {

    private static final Logger LOGGER = LogManager.getLogger(FilmController.class);
    private final FilmService filmService;
    private final AddFilmValidator addFilmValidator;

    public FilmController(FilmService filmService, AddFilmValidator addFilmValidator) {
        this.filmService = filmService;
        this.addFilmValidator = addFilmValidator;
    }

    public View createFilm(FilmDto filmDto) {
        View view;
        try {
            view = validateAddFilm(filmDto);
        } catch (ServiceException e) {
            view = receiveModelWithMessage("admin-add-film", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            view.addParameter("filmDto", filmDto);
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
            view = receiveModelWithMessage("admin-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug(e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }

    private View validateAddFilm(FilmDto filmDto) throws ServiceException {
        View view;
        String invalidateFields = addFilmValidator.validate(filmDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveModelWithMessage("admin-add-film", invalidateFields);
            view.addParameter("filmDto", filmDto);
        } else {
            filmService.createFilm(filmDto);
            view = receiveModelWithMessage("admin-personal-area", "Film added!");
        }
        return view;
    }

    private View receiveModelWithMessage(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        LOGGER.debug(error);
        return view;
    }
}
