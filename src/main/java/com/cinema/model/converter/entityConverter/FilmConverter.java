package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.resultSetConverter.UserResultSetConverter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

public class FilmConverter implements Converter<FilmDto, Film> {

    private static final Logger LOGGER = LogManager.getLogger(FilmConverter.class);

    @Override
    public Film convert(FilmDto filmDto) {
        Film film = new Film();
        film.setName(filmDto.getName());
        film.setNameEnglish(filmDto.getNameEnglish());
        film.setDescription(filmDto.getDescription());
        film.setDescriptionEnglish(filmDto.getDescriptionEnglish());
        film.setRunningTime(filmDto.getRunningTime());

        String filmReleaseDate = filmDto.getReleaseDate();
        Date currentDate = TimeConverter.convertStringToDate(filmReleaseDate, "yyyy-mm-dd");
        film.setReleaseDate(currentDate);
        LOGGER.debug("Film is converted from film dto!");
        return film;
    }
}
