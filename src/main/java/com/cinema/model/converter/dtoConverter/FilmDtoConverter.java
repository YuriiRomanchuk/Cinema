package com.cinema.model.converter.dtoConverter;

import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

import javax.servlet.http.HttpServletRequest;

public class FilmDtoConverter implements DtoConverter<FilmDto> {

    @Override
    public FilmDto convertFromHttpRequest(HttpServletRequest request) {
        FilmDto filmDto = new FilmDto();
        filmDto.setName(request.getParameter("name"));
        filmDto.setNameEnglish(request.getParameter("name_english"));
        filmDto.setReleaseDate(request.getParameter("release_date"));
        filmDto.setDescription(request.getParameter("description"));
        filmDto.setDescriptionEnglish(request.getParameter("description_english"));
        return filmDto;
    }

    public FilmDto convertFromFilmEntity(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setName(film.getName());
        filmDto.setNameEnglish(film.getNameEnglish());
        filmDto.setReleaseDate(String.valueOf(film.getReleaseDate()));
        filmDto.setDescription(film.getDescription());
        filmDto.setDescriptionEnglish(film.getDescriptionEnglish());
        return filmDto;
    }
}
