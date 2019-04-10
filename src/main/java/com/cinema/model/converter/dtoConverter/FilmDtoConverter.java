package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

import javax.servlet.http.HttpServletRequest;

public class FilmDtoConverter implements Converter<HttpServletRequest, FilmDto> {

    @Override
    public FilmDto convert(HttpServletRequest request) {
        FilmDto filmDto = new FilmDto();
        filmDto.setName(request.getParameter("name"));
        filmDto.setNameEnglish(request.getParameter("name_english"));
        filmDto.setReleaseDate(request.getParameter("release_date"));
        filmDto.setDescription(request.getParameter("description"));
        filmDto.setDescriptionEnglish(request.getParameter("description_english"));
        filmDto.setRunningTime(Integer.valueOf(request.getParameter("runningTime")));
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
        filmDto.setRunningTime(film.getRunningTime());
        return filmDto;
    }

    public FilmDto convertFromFilmSessionRequest(HttpServletRequest request) {
        FilmDto filmDto = new FilmDto();
        if (request.getParameter("film_id") != null) {
            filmDto.setId(Integer.valueOf(request.getParameter("film_id")));
        } else {
            filmDto.setId(-1);
        }
        return filmDto;
    }

}
