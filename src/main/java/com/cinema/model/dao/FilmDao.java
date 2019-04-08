package com.cinema.model.dao;

import com.cinema.model.entity.Film;
import com.cinema.model.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FilmDao implements GenericDao<Film> {

    private DataSource dataSource;
    private DataSource.SqlFunction<User> userConverter;

    public FilmDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Film entity) {

        final String query = "insert into films (name, name_english, release_date, description) values(?, ?, ?, ?)";

        dataSource.implementWrite(query, ps -> {
            try {
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getNameEnglish());
                ps.setTimestamp(3, new Timestamp(entity.getReleaseDate().getTime()));
                ps.setString(4, entity.getDescription());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, r -> entity.setId(r.getInt(1)));

    }

    @Override
    public Film findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public void update(Film entity) {

    }

    @Override
    public void delete(int id) {

    }
}
