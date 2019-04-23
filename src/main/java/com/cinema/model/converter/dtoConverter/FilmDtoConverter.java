package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class FilmDtoConverter implements Converter<HttpServletRequest, FilmDto> {

    private static final Logger LOGGER = LogManager.getLogger(FilmDtoConverter.class);

    @Override
    public FilmDto convert(HttpServletRequest request) {
        FilmDto filmDto = new FilmDto();
        filmDto.setName(request.getParameter("name"));
        filmDto.setNameEnglish(request.getParameter("name_english"));
        filmDto.setReleaseDate(request.getParameter("release_date"));
        filmDto.setDescription(request.getParameter("description"));
        filmDto.setDescriptionEnglish(request.getParameter("description_english"));
        filmDto.setRunningTime(Integer.valueOf(request.getParameter("running_time")));
        LOGGER.debug("Film dto is converted from request!");
        return filmDto;
    }

    public FilmDto convertFromFilmEntity(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setName(film.getName());
        filmDto.setNameEnglish(film.getNameEnglish());
        filmDto.setReleaseDate(TimeConverter.changeDataToStringFormat(film.getReleaseDate(), "yyyy-MM-dd"));
        filmDto.setDescription(film.getDescription());
        filmDto.setDescriptionEnglish(film.getDescriptionEnglish());
        filmDto.setRunningTime(film.getRunningTime());
        LOGGER.debug("Film dto is converted from entity!");
        return filmDto;
    }

    public FilmDto convertFromFilmSessionRequest(String film_id) {
        FilmDto filmDto = new FilmDto();
        if (film_id != null && !film_id.contains("...")) {
            filmDto.setId(Integer.valueOf(film_id));
        } else {
            filmDto.setId(-1);
        }
        LOGGER.debug("Film dto is converted from id");
        return filmDto;
    }

}
