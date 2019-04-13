package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.entity.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmResultSetConverter implements Converter<ResultSet, Film> {

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
        return film;
    }
}
