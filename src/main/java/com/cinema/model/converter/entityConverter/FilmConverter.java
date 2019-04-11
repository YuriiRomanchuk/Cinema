package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

import java.text.ParseException;
import java.util.Date;

public class FilmConverter implements Converter<FilmDto, Film> {

    @Override
    public Film convert(FilmDto filmDto) {

        Film film = new Film();
        film.setName(filmDto.getName());
        film.setNameEnglish(filmDto.getNameEnglish());
        film.setDescription(filmDto.getDescription());
        film.setDescriptionEnglish(filmDto.getDescriptionEnglish());
        film.setRunningTime(filmDto.getRunningTime());

        String filmReleaseDate = filmDto.getReleaseDate();
        try {
            Date currentDate = TimeConverter.convertStringDate(filmReleaseDate, "yyyy-mm-dd");
            film.setReleaseDate(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return film;
    }
}
