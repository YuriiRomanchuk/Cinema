package com.cinema.model.dao;

import com.cinema.model.entity.Film;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FilmDao implements GenericDao<Film> {

    private DataSource dataSource;
    private DataSource.SqlFunction<Film> filmConverter;

    public FilmDao(DataSource dataSource) {
        this.dataSource = dataSource;
        receiveConverter();
    }

    @Override
    public void insert(Film entity) {

        final String query = "insert into films (name, name_english, release_date, description, description_english) values(?, ?, ?, ?, ?)";

        dataSource.implementWrite(query, ps -> {
            try {
                ps.setString(1, entity.getName());
                ps.setString(2, entity.getNameEnglish());
                ps.setTimestamp(3, new Timestamp(entity.getReleaseDate().getTime()));
                ps.setString(4, entity.getDescription());
                ps.setString(5, entity.getDescriptionEnglish());
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
        return dataSource.receiveRecords("select id, name, name_english, description, release_date, description_english from films",
                filmConverter,
                preparedStatement -> {
                });
    }

    @Override
    public void update(Film entity) {

    }

    @Override
    public void delete(int id) {

    }

    private void receiveConverter() {
        filmConverter = rs -> {
            Film film = new Film();
            film.setName(rs.getString("name"));
            film.setNameEnglish(rs.getString("name_english"));
            film.setId(rs.getInt("id"));
            film.setDescription(rs.getString("description"));
            film.setReleaseDate(rs.getTimestamp("release_date"));
            film.setDescriptionEnglish(rs.getString("description_english"));
            return film;
        };
    }
}
