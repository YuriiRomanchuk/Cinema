package com.cinema.model.converter.EntityConverter;

import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FilmConverter implements EntityConverter<FilmDto, Film> {

    @Override
    public Film convert(FilmDto filmDto) throws ParseException {

        Film film = new Film();
        film.setName(filmDto.getName());
        film.setNameEnglish(filmDto.getNameEnglish());
        film.setDescription(filmDto.getDescription());

        String filmReleaseDate = filmDto.getReleaseDate();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        film.setReleaseDate(format.parse(filmReleaseDate));

        return film;
    }
}
