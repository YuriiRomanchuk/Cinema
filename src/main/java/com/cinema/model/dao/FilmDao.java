package com.cinema.model.dao;

import com.cinema.model.converter.resultSetConverter.FilmResultSetConverter;
import com.cinema.model.entity.Film;

import java.sql.Timestamp;
import java.util.List;

public class FilmDao implements GenericDao<Film> {

    private final DataSource dataSource;
    private final FilmResultSetConverter filmResultSetConverter;

    public FilmDao(DataSource dataSource, FilmResultSetConverter filmResultSetConverter) {
        this.dataSource = dataSource;
        this.filmResultSetConverter = filmResultSetConverter;
    }

    @Override
    public void insert(Film entity) {

        final String query = "insert into films (name, name_english, release_date, description, description_english, running_time) values(?, ?, ?, ?, ?, ?)";

        dataSource.update(query, ps -> {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getNameEnglish());
            ps.setTimestamp(3, new Timestamp(entity.getReleaseDate().getTime()));
            ps.setString(4, entity.getDescription());
            ps.setString(5, entity.getDescriptionEnglish());
            ps.setInt(6, entity.getRunningTime());
        }, r -> entity.setId(r.getInt(1)));
    }

    @Override
    public Film findById(int id) {
        return null;
    }

    @Override
    public List<Film> findAll() {
        return dataSource.receiveRecords("select id as film_id, name, name_english, description," +
                        " release_date, description_english, running_time from films",
                resultSet -> filmResultSetConverter.convert(resultSet),
                preparedStatement -> {
                });
    }

    @Override
    public void update(Film entity) {

    }

    @Override
    public void delete(int id) {

    }
}
