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
        String currentDate = (date_filter == null) ? String.valueOf(new Date()) :
                String.valueOf(TimeConverter.convertStringToDate(date_filter, "yyyy-MM-dd"));

        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(currentDate);
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmSessionRequest(request.getParameter("film_filter")));

        return filmSessionDto;
    }

    public FilmSessionDto convertFilmSessionWithLineAdd(HttpServletRequest request) {
        return convertFilmSessionWithLine(request, request.getParameter("add-session"));
    }

    public FilmSessionDto convertFilmSessionWithLineDelete(HttpServletRequest request) {
        return convertFilmSessionWithLine(request, request.getParameter("delete-session"));
    }

    public int convertFilmSessionWithLineShow(HttpServletRequest request) {
        String numberOfLine = request.getParameter("show-session");
        return Integer.valueOf(request.getParameter("session_id_" + numberOfLine).trim());
    }

    public int convertFilmSessionWithLineBuy(HttpServletRequest request) {
        String numberOfLine = request.getParameter("show-user-session");
        return Integer.valueOf(request.getParameter("session_id_" + numberOfLine).trim());
    }

    private FilmSessionDto convertFilmSessionWithLine(HttpServletRequest request, String numberOfLine) {
        String session_date = String.valueOf(TimeConverter.convertStringToDate(request.getParameter("session_date_" + numberOfLine), "yyyy-MM-dd kk:mm:ss"));
        String session_id = request.getParameter("session_id_" + numberOfLine).trim();
        String session_film_id = request.getParameter("session_film_" + numberOfLine);
        String session_room_id = request.getParameter("session_room_" + numberOfLine);
        return convertFilmSessionByIdes(session_id, session_film_id, session_room_id, session_date);
    }

    public FilmSessionDto convertFilmSessionByIdes(String session_id, String session_film_id, String session_room_id, String session_date) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setDate(session_date);
        filmSessionDto.setId(Integer.valueOf(session_id));
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmSessionRequest(session_film_id));
        filmSessionDto.setRoomDto(roomDtoConverter.convertForRoomId(session_room_id));

        return filmSessionDto;
    }

    public FilmSessionDto convertFromFilmEntity(FilmSession filmSession) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setRoomDto(roomDtoConverter.convertFromRoomEntity(filmSession.getRoom()));
        filmSessionDto.setFilmDto(filmDtoConverter.convertFromFilmEntity(filmSession.getFilm()));
        filmSessionDto.setId(filmSession.getId());
        filmSessionDto.setDate(TimeConverter.changeDataToStringFormat(filmSession.getDate(), "yyyy-MM-dd kk:mm:ss"));
        return filmSessionDto;
    }

    public int receiveFilmSessionId(HttpServletRequest request) {
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/main", "");
        String[] splitURI = requestURI.split("/");
        return Integer.valueOf(splitURI[splitURI.length - 1]);
    }

}
