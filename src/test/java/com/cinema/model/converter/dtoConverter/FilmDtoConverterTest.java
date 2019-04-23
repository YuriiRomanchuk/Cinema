package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilmDtoConverterTest {

    @InjectMocks
    private FilmDtoConverter filmDtoConverter;

    @Test
    public void convert() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getParameter("name")).thenReturn("Пірати");
        when(request.getParameter("name_english")).thenReturn("Pirates");
        when(request.getParameter("release_date")).thenReturn("2015-01-01");
        when(request.getParameter("description")).thenReturn("хороший фильм");
        when(request.getParameter("description_english")).thenReturn("good film");
        when(request.getParameter("running_time")).thenReturn("2");

        FilmDto filmDto = receiveFilmDtoTest();
        FilmDto convert = filmDtoConverter.convert(request);

        Assert.assertTrue(new ReflectionEquals(convert).matches(filmDto));
    }

    @Test
    public void convertFromFilmEntity() {

        Film film = new Film();
        film.setName("Пірати");
        film.setNameEnglish("Pirates");
        film.setReleaseDate(TimeConverter.convertStringToDate("2015-01-01", "yyyy-mm-dd"));
        film.setDescription("хороший фильм");
        film.setDescriptionEnglish("good film");
        film.setRunningTime(2);

        FilmDto filmDto = receiveFilmDtoTest();
        FilmDto convert = filmDtoConverter.convertFromFilmEntity(film);

        Assert.assertTrue(new ReflectionEquals(convert).matches(filmDto));
    }

    @Test
    public void convertFromFilmSessionRequestById() {

        FilmDto filmDto = new FilmDto();
        int film_id = 1;

        filmDto.setId(film_id);
        FilmDto convert = filmDtoConverter.convertFromFilmSessionRequestById(String.valueOf(film_id));
        Assert.assertTrue(new ReflectionEquals(convert).matches(filmDto));
    }

    private FilmDto receiveFilmDtoTest() {

        FilmDto filmDto = new FilmDto();
        filmDto.setName("Пірати");
        filmDto.setNameEnglish("Pirates");
        filmDto.setReleaseDate("2015-01-01");
        filmDto.setDescription("хороший фильм");
        filmDto.setDescriptionEnglish("good film");
        filmDto.setRunningTime(2);

        return filmDto;
    }
}