package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.utility.TimeConverter;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.model.entity.FilmSession;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class FilmSessionDtoConverter implements Converter<HttpServletRequest, FilmSessionDto> {

    private final FilmDtoConverter filmDtoConverter;
    private final RoomDtoConverter roomDtoConverter;

    public FilmSessionDtoConverter(FilmDtoConverter filmDtoConverter, RoomDtoConverter roomDtoConverter) {
        this.filmDtoConverter = filmDtoConverter;
        this.roomDtoConverter = roomDtoConverter;
    }

    @Override
    public FilmSessionDto convert(HttpServletRequest request) {

        String date_filter = request.getParameter("date_filter");
        String currentDate = (date_filter== null) ? String.valueOf(new Date()) :
                String.valueOf(TimeConverter.convertStringToDate(date_filter, "yyyy-MM-dd"));

        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(currentDate);
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmSessionRequest(request));

        return filmSessionDto;
    }

    /*public FilmSessionDto convertSessionWithLine(HttpServletRequest request) {

        String numberOfLine = request.getParameter("add-session");changeDataToStringFormat

        if (currentDate != null) {
            currentDate = TimeConverter.changeDataToStringFormat(request.getParameter("date_filter"), "yyyy-MM-dd");
        }
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(currentDate);
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmSessionRequest(request));

        return filmSessionDto;
    }
*/

    public FilmSessionDto convertFromFilmEntity(FilmSession filmSession) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setRoomDto(roomDtoConverter.convertFromRoomEntity(filmSession.getRoom()));
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmEntity(filmSession.getFilm()));
        filmSessionDto.setId(filmSession.getId());
        filmSessionDto.setDate(TimeConverter.changeDataToStringFormat(filmSession.getDate(), "yyyy-MM-dd hh:mm:ss"));
        return filmSessionDto;
    }
}
