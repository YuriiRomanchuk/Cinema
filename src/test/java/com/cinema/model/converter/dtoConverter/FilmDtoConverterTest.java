package com.cinema.model.converter.dtoConverter;

import com.cinema.model.dto.FilmDto;
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
        when(request.getParameter("release_date")).thenReturn("2015");
        when(request.getParameter("description")).thenReturn("хороший фильм");
        when(request.getParameter("description_english")).thenReturn("good film");
        when(request.getParameter("running_time")).thenReturn("2");

        FilmDto filmDto = new FilmDto();
        filmDto.setName("Пірати");
        filmDto.setNameEnglish("Pirates");
        filmDto.setReleaseDate("2015");
        filmDto.setDescription("хороший фильм");
        filmDto.setDescriptionEnglish("good film");
        filmDto.setRunningTime(2);

        FilmDto convert = filmDtoConverter.convert(request);

        Assert.assertTrue(new ReflectionEquals(convert).matches(filmDto));
    }

    @Test
    public void convertFromFilmEntity() {
    }

    @Test
    public void convertFromFilmSessionRequest() {
    }
}