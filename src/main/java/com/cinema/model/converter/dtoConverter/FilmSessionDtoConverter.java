package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.FilmSessionDto;

import javax.servlet.http.HttpServletRequest;

public class FilmSessionDtoConverter implements Converter<HttpServletRequest, FilmSessionDto> {

    private final FilmDtoConverter filmDtoConverter;

    public FilmSessionDtoConverter(FilmDtoConverter filmDtoConverter) {
        this.filmDtoConverter = filmDtoConverter;
    }

    @Override
    public FilmSessionDto convert(HttpServletRequest request) {

        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(request.getParameter("data"));
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmSessionRequest(request));

        return filmSessionDto;
    }
}
