package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.dtoConverter.FilmDtoConverter;
import com.cinema.model.converter.entityConverter.FilmConverter;
import com.cinema.model.dao.FilmDao;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FilmServiceTest {
    @Mock
    private FilmDao filmDao;

    @Mock
    private FilmConverter filmConverter;

    @Mock
    private FilmDtoConverter filmDtoConverter;

    @InjectMocks
    private FilmService filmService;

    @Test
    public void createFilm() throws ServiceException {
        FilmDto filmDto = new FilmDto();
        Film film = new Film();
        when(filmConverter.convert(filmDto)).thenReturn(film);
        filmService.createFilm(filmDto);
        verify(filmDao).insert(film);
    }

    @Test
    public void receiveAllFilmsDto() throws ServiceException {
        FilmDto filmDto1 = new FilmDto();
        FilmDto filmDto2 = new FilmDto();
        Film film1 = new Film();
        Film film2 = new Film();

        List<Film> films = new ArrayList<>();
        films.add(film1);
        films.add(film2);

        when(filmDao.findAll()).thenReturn(films);
        when(filmDtoConverter.convertFromFilmEntity(film1)).thenReturn(filmDto1);
        when(filmDtoConverter.convertFromFilmEntity(film2)).thenReturn(filmDto2);

        List<FilmDto> filmsDto = filmService.receiveAllFilmsDto();
        assertEquals(2, filmsDto.size());

        assertEquals(filmDto1, filmsDto.get(0));
        assertEquals(filmDto2, filmsDto.get(1));
    }
}