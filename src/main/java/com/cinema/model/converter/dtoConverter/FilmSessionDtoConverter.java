package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.model.entity.FilmSession;

import javax.servlet.http.HttpServletRequest;

public class FilmSessionDtoConverter implements Converter<HttpServletRequest, FilmSessionDto> {

    private final FilmDtoConverter filmDtoConverter;
    private final RoomDtoConverter roomDtoConverter;

    public FilmSessionDtoConverter(FilmDtoConverter filmDtoConverter, RoomDtoConverter roomDtoConverter) {
        this.filmDtoConverter = filmDtoConverter;
        this.roomDtoConverter = roomDtoConverter;
    }

    @Override
    public FilmSessionDto convert(HttpServletRequest request) {

        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(request.getParameter("data"));
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmSessionRequest(request));

        return filmSessionDto;
    }

    public FilmSessionDto convertFromFilmEntity(FilmSession filmSession) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setRoomDto(roomDtoConverter.convertFromRoomEntity(filmSession.getRoom()));
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmEntity(filmSession.getFilm()));
        filmSessionDto.setId(filmSession.getId());
        filmSessionDto.setDate(String.valueOf(filmSession.getDate()));
        return filmSessionDto;
    }
}
