package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.FilmDtoConverter;
import com.cinema.model.converter.entityConverter.FilmConverter;
import com.cinema.model.dao.FilmDao;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

import java.util.List;
import java.util.stream.Collectors;

public class FilmService {

    private final FilmDao filmDao;
    private final FilmConverter filmConverter;
    private final FilmDtoConverter filmDtoConverter;

    public FilmService(FilmDao filmDao, FilmConverter filmConverter, FilmDtoConverter filmDtoConverter) {
        this.filmDao = filmDao;
        this.filmConverter = filmConverter;
        this.filmDtoConverter = filmDtoConverter;
    }

    public void createFilm(FilmDto filmDto) throws ServiceException {
        try {
            Film film = filmConverter.convert(filmDto);
            filmDao.insert(film);
        } catch (Exception e) {
            throw new ServiceException("Create film failed", e);
        }
    }

    public List<FilmDto> receiveAllFilmsDto() throws ServiceException {
        try {
            return filmDao.findAll().stream().map(filmDtoConverter::convertFromFilmEntity).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Create film dto failed", e);
        }

    }
}
