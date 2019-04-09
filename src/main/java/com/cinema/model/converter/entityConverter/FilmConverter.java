package com.cinema.model.converter.entityConverter;

import com.cinema.model.dto.FilmDto;
import com.cinema.model.entity.Film;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FilmConverter implements EntityConverter<FilmDto, Film> {

    @Override
    public Film convert(FilmDto filmDto) {

        Film film = new Film();
        film.setName(filmDto.getName());
        film.setNameEnglish(filmDto.getNameEnglish());
        film.setDescription(filmDto.getDescription());

        String filmReleaseDate = filmDto.getReleaseDate();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-mm-dd");
        try {
            film.setReleaseDate(format.parse(filmReleaseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return film;
    }
}
