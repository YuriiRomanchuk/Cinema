package com.cinema.model.dao;

import com.cinema.model.converter.utility.DaoConverter;
import com.cinema.model.entity.Ticket;

import java.util.List;

public class TicketDao implements GenericDao<Ticket> {

    private final DataSource dataSource;
    private DataSource.SqlFunction<Ticket> ticketConverter;

    public TicketDao(DataSource dataSource) {
        this.dataSource = dataSource;
        receiveConverter();
    }


    @Override
    public void insert(Ticket entity) {

    }

    @Override
    public Ticket findById(int id) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Ticket> findTicketBySessionId(int filmSessionId) {

        return dataSource.receiveRecords("Select temp2.*, films.*, rooms.name as room_name, rooms.name_english as room_name_english " +
                        "FROM " +
                        "(Select  ticket_id, session_id, place_id, user_id, session.film_id, places.room_id, " +
                        "        session.date as session_date, places.row as place_row, places.place as place_place, " +
                        "        users.* " +
                        "FROM " +
                        "(Select id as ticket_id, session_id, place_id, user_id from tickets where session_id = ?) temp " +
                        "left join session on session_id = session.id left join users on user_id = users.id " +
                        "left join places on place_id = places.id) temp2 left join films on temp2.film_id = films.id " +
                        "LEFT JOIN rooms on temp2.room_id = rooms.id",
                ticketConverter,
                preparedStatement ->
                {
                    preparedStatement.setInt(1, filmSessionId);
                });
    }

    private void receiveConverter() {
        ticketConverter = rs -> {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("ticket_id"));
            ticket.setUser(DaoConverter.convertResultSetToUser(rs));
            ticket.setRoomPlace(DaoConverter.convertResultSetToRoomPlace(rs));
            ticket.setFilmSession(DaoConverter.convertResultSetToFilmSession(rs));
            return ticket;
        };
    }
}
