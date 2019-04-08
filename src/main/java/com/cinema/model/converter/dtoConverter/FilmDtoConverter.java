package com.cinema.model.converter.dtoConverter;

import com.cinema.model.dto.FilmDto;

import javax.servlet.http.HttpServletRequest;

public class FilmDtoConverter implements DtoConverter<FilmDto> {

    @Override
    public FilmDto convert(HttpServletRequest request) {

        FilmDto filmDto = new FilmDto();
        filmDto.setName(request.getParameter("name"));
        filmDto.setNameEnglish(request.getParameter("name_english"));
        filmDto.setReleaseDate(request.getParameter("release_date"));
        filmDto.setDescription(request.getParameter("description"));
        return filmDto;
    }
}
