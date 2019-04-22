package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.entity.FilmSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmSessionResultSetConverter implements Converter<ResultSet, FilmSession> {

    private static final Logger LOGGER = LogManager.getLogger(FilmSessionResultSetConverter.class);
    private final FilmResultSetConverter filmResultSetConverter;
    private final RoomResultSetConverter roomResultSetConverter;

    public FilmSessionResultSetConverter(FilmResultSetConverter filmResultSetConverter, RoomResultSetConverter roomResultSetConverter) {
        this.filmResultSetConverter = filmResultSetConverter;
        this.roomResultSetConverter = roomResultSetConverter;
    }

    @Override
    public FilmSession convert(ResultSet resultSet) throws SQLException {
        FilmSession filmSession = new FilmSession();
        filmSession.setId(resultSet.getInt("session_id"));
        filmSession.setDate(resultSet.getTimestamp("session_date"));
        filmSession.setFilm(filmResultSetConverter.convert(resultSet));
        filmSession.setRoom(roomResultSetConverter.convert(resultSet));
        LOGGER.debug("Film session result set is converted!");
        return filmSession;
    }
}
