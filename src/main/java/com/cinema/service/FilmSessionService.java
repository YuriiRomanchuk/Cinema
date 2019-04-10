package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.FilmDtoConverter;
import com.cinema.model.converter.entityConverter.FilmSessionConverter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dao.FilmSessionDao;
import com.cinema.model.dto.FilmSessionDto;

import java.util.Date;

public class FilmSessionService {

    private final FilmSessionDao filmSessionDao;
    private final FilmDtoConverter filmDtoConverter;
    private final FilmSessionConverter filmSessionConverter;

    public FilmSessionService(FilmSessionDao filmSessionDao, FilmDtoConverter filmDtoConverter, FilmSessionConverter filmSessionConverter) {
        this.filmSessionDao = filmSessionDao;
        this.filmDtoConverter = filmDtoConverter;
        this.filmSessionConverter = filmSessionConverter;
    }

    public void showFilmSession(FilmSessionDto filmSessionDto) throws ServiceException {

        try {
            Date currentFilmSessionDate = TimeConverter.convertStringDate(filmSessionDto.getDate());
            Date beginOfDay = TimeConverter.receiveBeginOfDay(currentFilmSessionDate);
            Date endOfDay = TimeConverter.receiveEndOfDay(currentFilmSessionDate);
            filmSessionDao.findByFilters(beginOfDay, endOfDay, filmSessionDto.getFilmDto().getId());

        } catch (Exception e) {
            throw new ServiceException("Create film session failed", e);
        }


    }
}
