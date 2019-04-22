package com.cinema.config;

import com.cinema.model.converter.resultSetConverter.*;
import com.cinema.model.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataComponentInitializer {

    private static final Logger LOGGER = LogManager.getLogger(DataComponentInitializer.class);

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
    private final FilmSaleResultSetConverter filmSaleResultSetConverter;

    private DataComponentInitializer() {

        LOGGER.debug("DataComponentInitializer created");

        dataSource = new DataSource();

        filmResultSetConverter = new FilmResultSetConverter();
        userResultSetConverter = new UserResultSetConverter();
        roomResultSetConverter = new RoomResultSetConverter();
        filmSessionResultSetConverter = new FilmSessionResultSetConverter(filmResultSetConverter, roomResultSetConverter);
        roomPlaceResultSetConverter = new RoomPlaceResultSetConverter(roomResultSetConverter);
        ticketResultSetConverter = new TicketResultSetConverter(userResultSetConverter, roomPlaceResultSetConverter, filmSessionResultSetConverter);
        filmSaleResultSetConverter = new FilmSaleResultSetConverter(filmResultSetConverter);

        userDao = new UserDao(dataSource, userResultSetConverter);
        filmDao = new FilmDao(dataSource, filmResultSetConverter);
        roomDao = new RoomDao(dataSource, roomResultSetConverter);
        roomPlaceDao = new RoomPlaceDao(dataSource, roomPlaceResultSetConverter);
        filmSessionDao = new FilmSessionDao(dataSource, filmSessionResultSetConverter, filmSaleResultSetConverter);
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
