package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.model.entity.FilmSession;

public class FilmSessionConverter implements Converter<FilmSessionDto, FilmSession> {

    @Override
    public FilmSession convert(FilmSessionDto filmSessionDto) {
    /*    FilmSession filmSession = new FilmSession();
        filmSession.setDate(TimeConverter.convertStringToDate(filmSessionDto.getDate(), "E MMM dd HH:mm:ss Z yyyy"));
        filmSession.setFilm(filmConverter.convert(filmSessionDto.getFilmDto()));
        filmSession.setRoom(roomConverter.convert(filmSessionDto.getRoomDto()));*/
        return null;
    }
}
