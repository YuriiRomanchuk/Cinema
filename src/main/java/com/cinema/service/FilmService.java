package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.FilmDtoConverter;
import com.cinema.model.converter.entityConverter.FilmConverter;
import com.cinema.model.dao.FilmDao;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmService {

    private FilmDao filmDao;
    private FilmConverter filmConverter;
    private FilmDtoConverter filmDtoConverter;

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
            e.printStackTrace();
            throw new ServiceException("Create film failed", e);
        }
    }

    public List<FilmDto> receiveAllFilmsDto() throws ServiceException{
        try {
            List<FilmDto> filmsDto = new ArrayList<>();
            List<Film> films = filmDao.findAll();

            films.forEach(f -> filmsDto.add(filmDtoConverter.convertFromFilmEntity(f)));
            return filmsDto;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Create film failed", e);
        }

    }
}
