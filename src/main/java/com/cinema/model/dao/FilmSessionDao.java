package com.cinema.model.dao;

import com.cinema.model.converter.utility.DaoConverter;
import com.cinema.model.entity.FilmSession;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class FilmSessionDao implements GenericDao<FilmSession> {

    private final DataSource dataSource;
    private DataSource.SqlFunction<FilmSession> filmSessionConverter;

    public FilmSessionDao(DataSource dataSource) {
        this.dataSource = dataSource;
        receiveConverter();
    }

    @Override
    public void insert(FilmSession entity) {

        final String query = "insert into session (film_id, room_id, date) values(?, ?, ?)";

        dataSource.implementWrite(query, ps -> {
            ps.setInt(1, entity.getFilm().getId());
            ps.setInt(2, entity.getRoom().getId());
            ps.setTimestamp(3, new Timestamp(entity.getDate().getTime()));
        }, r -> entity.setId(r.getInt(1)));
    }

    @Override
    public FilmSession findById(int id) {
        return null;
    }

    @Override
    public List<FilmSession> findAll() {
        return null;
    }

    @Override
    public void update(FilmSession entity) {

    }

    public List<FilmSession> findByFilters(Date beginOfDay, Date endOfDay, int film_id) {

        String query = "SELECT temp.id as session_id, film_id, room_id, date as session_date" +
                ", films.*,rooms.name as room_name, rooms.name_english as room_name_english " +
                "FROM (select id, film_id, room_id, date from session where date >= ? and date <= ? and 1 = 1) " +
                "temp LEFT JOIN films ON film_id = films.id LEFT JOIN rooms ON room_id = rooms.id order by date";

        if (film_id >= 0) {
            query = query.replaceAll("1 = 1", "film_id = ?");
        }

        List<FilmSession> filmsSession = dataSource.receiveRecords(query,
                filmSessionConverter,
                preparedStatement -> {
                    preparedStatement.setTimestamp(1, new Timestamp(beginOfDay.getTime()));
                    preparedStatement.setTimestamp(2, new Timestamp(endOfDay.getTime()));
                    if (film_id >= 0) {
                        preparedStatement.setInt(3, film_id);
                    }
                });

        return filmsSession;
    }


    @Override
    public void delete(int filmId) {
        final String query = "delete from session where session.id = ?";

        dataSource.implementWrite(query, ps -> {
            ps.setInt(1, filmId);
        }, r -> {
        });
    }

    private void receiveConverter() {
        filmSessionConverter = rs -> DaoConverter.convertResultSetToFilmSession(rs);
    }

    public void insert(int filmId, int roomId, Date sessionDate) {

        final String query = "insert into session (film_id, room_id, date) values(?, ?, ?)";

        dataSource.implementWrite(query, ps -> {
            ps.setInt(1, filmId);
            ps.setInt(2, roomId);
            ps.setTimestamp(3, new Timestamp(sessionDate.getTime()));
        }, r -> {
        });
    }
}
