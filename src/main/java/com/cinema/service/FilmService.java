package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.entityConverter.FilmConverter;
import com.cinema.model.dao.FilmDao;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

public class FilmService {

    private FilmDao filmDao;
    private FilmConverter filmConverter;

    public FilmService(FilmDao filmDao, FilmConverter filmConverter) {
        this.filmDao = filmDao;
        this.filmConverter = filmConverter;
    }

    public void createFilm(FilmDto filmDto) throws ServiceException {
        try {
            Film film = filmConverter.convert(filmDto);
            filmDao.insert(film);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Create film failed", e);
        }
    }

}
