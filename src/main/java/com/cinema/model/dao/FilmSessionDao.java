package com.cinema.model.dao;

import com.cinema.model.converter.resultSetConverter.FilmSaleResultSetConverter;
import com.cinema.model.converter.resultSetConverter.FilmSessionResultSetConverter;
import com.cinema.model.entity.FilmSession;
import com.cinema.model.statistics.FilmSale;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class FilmSessionDao implements GenericDao<FilmSession> {

    private final DataSource dataSource;
    private final FilmSessionResultSetConverter filmSessionResultSetConverter;
    private final FilmSaleResultSetConverter filmSaleResultSetConverter;

    public FilmSessionDao(DataSource dataSource,
                          FilmSessionResultSetConverter filmSessionResultSetConverter,
                          FilmSaleResultSetConverter filmSaleResultSetConverter) {
        this.dataSource = dataSource;
        this.filmSessionResultSetConverter = filmSessionResultSetConverter;
        this.filmSaleResultSetConverter = filmSaleResultSetConverter;
    }

    @Override
    public void insert(FilmSession entity) {

        final String query = "insert into session (film_id, room_id, date) values(?, ?, ?)";

        dataSource.update(query, ps -> {
            ps.setInt(1, entity.getFilm().getId());
            ps.setInt(2, entity.getRoom().getId());
            ps.setTimestamp(3, new Timestamp(entity.getDate().getTime()));
        }, r -> entity.setId(r.getInt(1)));
    }

    @Override
    public FilmSession findById(int id) {

        String query = "SELECT temp.id as session_id, film_id, room_id, date as session_date" +
                ", films.*,rooms.name as room_name, rooms.name_english as room_name_english " +
                "FROM (select id, film_id, room_id, date from session where id = ?) " +
                "temp LEFT JOIN films ON film_id = films.id LEFT JOIN rooms ON room_id = rooms.id order by date";

        return dataSource.receiveFirstRecord(query,
                resultSet -> filmSessionResultSetConverter.convert(resultSet),
                preparedStatement -> {
                    preparedStatement.setInt(1, id);
                }).get();
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

        return dataSource.receiveRecords(query,
                resultSet -> filmSessionResultSetConverter.convert(resultSet),
                preparedStatement -> {
                    preparedStatement.setTimestamp(1, new Timestamp(beginOfDay.getTime()));
                    preparedStatement.setTimestamp(2, new Timestamp(endOfDay.getTime()));
                    if (film_id >= 0) {
                        preparedStatement.setInt(3, film_id);
                    }
                });
    }


    @Override
    public void delete(int filmSessionId) {

        QueryData[] queriesData = new QueryData[2];
        queriesData[0] = (new QueryData("delete from tickets where session_id = ?", ps -> {
            ps.setInt(1, filmSessionId);
        }));
        queriesData[1] = (new QueryData("delete from session where session.id = ?", ps -> {
            ps.setInt(1, filmSessionId);
        }));

        dataSource.transactionUpdate(queriesData);
    }

    public void insert(int filmId, int roomId, Date sessionDate) {

        final String query = "insert into session (film_id, room_id, date) values(?, ?, ?)";

        dataSource.update(query, ps -> {
            ps.setInt(1, filmId);
            ps.setInt(2, roomId);
            ps.setTimestamp(3, new Timestamp(sessionDate.getTime()));
        }, r -> {
        });
    }

    public List<FilmSale> findFilmSalesByDate(Date beginOfDay, Date endOfDay) {

        final String query = "SELECT temp2.*, films.* FROM (SELECT film_id, SUM(1) as number_of_tickets FROM " +
                "  (SELECT * FROM session where date >= ? and date <= ?) temp LEFT JOIN tickets ON temp.id = tickets.session_id GROUP BY temp.film_id) temp2 " +
                " LEFT JOIN films ON temp2.film_id = films.id";

        return dataSource.receiveRecords(query,
                resultSet -> filmSaleResultSetConverter.convert(resultSet),
                preparedStatement -> {
                    preparedStatement.setTimestamp(1, new Timestamp(beginOfDay.getTime()));
                    preparedStatement.setTimestamp(2, new Timestamp(endOfDay.getTime()));
                });
    }
}
