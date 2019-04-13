package com.cinema.config;

import com.cinema.model.converter.resultSetConverter.*;
import com.cinema.model.dao.*;

public class DataComponentInitializer {

    private static DataComponentInitializer initializer;

    private final DataSource dataSource;
    private final UserDao userDao;
    private final FilmDao filmDao;
    private final RoomDao roomDao;
    private final RoomPlaceDao roomPlaceDao;
    private final FilmSessionDao filmSessionDao;
    private final TicketDao ticketDao;

    private final FilmResultSetConverter filmResultSetConverter;
    private final FilmSessionResultSetConverter filmSessionResultSetConverter;
    private final RoomPlaceResultSetConverter roomPlaceResultSetConverter;
    private final RoomResultSetConverter roomResultSetConverter;
    private final TicketResultSetConverter ticketResultSetConverter;
    private final UserResultSetConverter userResultSetConverter;

    private DataComponentInitializer() {

        dataSource = new DataSource();

        filmResultSetConverter = new FilmResultSetConverter();
        userResultSetConverter = new UserResultSetConverter();
        roomResultSetConverter = new RoomResultSetConverter();
        filmSessionResultSetConverter = new FilmSessionResultSetConverter(filmResultSetConverter, roomResultSetConverter);
        roomPlaceResultSetConverter = new RoomPlaceResultSetConverter(roomResultSetConverter);
        ticketResultSetConverter = new TicketResultSetConverter(userResultSetConverter, roomPlaceResultSetConverter, filmSessionResultSetConverter);

        userDao = new UserDao(dataSource, userResultSetConverter);
        filmDao = new FilmDao(dataSource, filmResultSetConverter);
        roomDao = new RoomDao(dataSource, roomResultSetConverter);
        roomPlaceDao = new RoomPlaceDao(dataSource, roomPlaceResultSetConverter);
        filmSessionDao = new FilmSessionDao(dataSource, filmSessionResultSetConverter);
        ticketDao = new TicketDao(dataSource, ticketResultSetConverter);
    }


    public static DataComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (WebComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new DataComponentInitializer();
                }
            }
        }

        return initializer;
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public FilmDao getFilmDao() {
        return filmDao;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public RoomPlaceDao getRoomPlaceDao() {
        return roomPlaceDao;
    }

    public FilmSessionDao getFilmSessionDao() {
        return filmSessionDao;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }
}
