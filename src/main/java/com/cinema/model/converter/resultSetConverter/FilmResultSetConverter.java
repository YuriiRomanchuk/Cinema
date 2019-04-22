package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.dtoConverter.TicketDtoConverter;
import com.cinema.model.entity.Film;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmResultSetConverter implements Converter<ResultSet, Film> {

    private static final Logger LOGGER = LogManager.getLogger(FilmResultSetConverter.class);

    @Override
    public Film convert(ResultSet resultSet) throws SQLException {
        Film film = new Film();
        film.setName(resultSet.getString("name"));
        film.setNameEnglish(resultSet.getString("name_english"));
        film.setId(resultSet.getInt("film_id"));
        film.setDescription(resultSet.getString("description"));
        film.setReleaseDate(resultSet.getTimestamp("release_date"));
        film.setDescriptionEnglish(resultSet.getString("description_english"));
        film.setRunningTime(resultSet.getInt("running_time"));
        LOGGER.debug("Film result set is converted!");
        return film;
    }
}
