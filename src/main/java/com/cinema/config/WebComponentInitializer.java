package com.cinema.config;

import com.cinema.controller.*;
import com.cinema.model.converter.UserConverter;
import com.cinema.model.converter.dtoConverter.*;
import com.cinema.model.converter.entityConverter.FilmConverter;
import com.cinema.model.converter.entityConverter.FilmSessionConverter;
import com.cinema.model.converter.entityConverter.RoomConverter;
import com.cinema.model.converter.entityConverter.RoomPlaceConverter;
import com.cinema.model.dao.*;
import com.cinema.service.*;
import com.cinema.servlet.RequestResolver;

public class WebComponentInitializer {

    private static WebComponentInitializer initializer;
    private final RequestResolver requestResolver;

    private final WelcomeController welcomeController;
    private final UserController userController;
    private final ChangeLanguageController changeLanguageController;
    private final FilmController filmController;
    private final RoomController roomController;
    private final RoomPlaceController roomPlaceController;
    private final FilmSessionController filmSessionController;

    private final FilmService filmService;
    private final UserService userService;
    private final RoomService roomService;
    private final RoomPlaceService roomPlaceService;
    private final FilmSessionService filmSessionService;

    private final UserLoginDtoConverter userLoginDtoConverter;
    private final FilmDtoConverter filmDtoConverter;
    private final RoomDtoConverter roomDtoConverter;
    private final RoomPlaceDtoConverter roomPlaceDtoConverter;
    private final FilmSessionDtoConverter filmSessionDtoConverter;

    private final UserConverter userConverter;
    private final FilmConverter filmConverter;
    private final RoomConverter roomConverter;
    private final RoomPlaceConverter roomPlaceConverter;
    private final FilmSessionConverter filmSessionConverter;
/*
    private final UserDao userDao;
    private final FilmDao filmDao;
    private final RoomDao roomDao;
    private final RoomPlaceDao roomPlaceDao;
    private final FilmSessionDao filmSessionDao;*/

    private WebComponentInitializer() {

      /*  DataSource dataSource = new DataSource();*/
/*        userDao = new UserDao(dataSource, userResultSetConverter);
        filmDao = new FilmDao(dataSource, filmResultSetConverter);
        roomDao = new RoomDao(dataSource, roomResultSetConverter);
        roomPlaceDao = new RoomPlaceDao(dataSource, roomPlaceResultSetConverter);
        filmSessionDao = new FilmSessionDao(dataSource, filmSessionResultSetConverter);*/

        DataComponentInitializer dataComponentInitializer = DataComponentInitializer.getInstance();

        userLoginDtoConverter = new UserLoginDtoConverter();
        filmDtoConverter = new FilmDtoConverter();
        roomDtoConverter = new RoomDtoConverter();
        roomPlaceDtoConverter = new RoomPlaceDtoConverter(roomDtoConverter);
        filmSessionDtoConverter = new FilmSessionDtoConverter(filmDtoConverter, roomDtoConverter);

        userConverter = new UserConverter();
        filmConverter = new FilmConverter();
        roomConverter = new RoomConverter();
        roomPlaceConverter = new RoomPlaceConverter(roomConverter);
        filmSessionConverter = new FilmSessionConverter();

        userService = new UserService(dataComponentInitializer.getUserDao());
        filmService = new FilmService(dataComponentInitializer.getFilmDao(), filmConverter, filmDtoConverter);
        roomService = new RoomService(dataComponentInitializer.getRoomDao(), roomConverter, roomDtoConverter);
        roomPlaceService = new RoomPlaceService(dataComponentInitializer.getRoomPlaceDao(), roomPlaceConverter);
        filmSessionService = new FilmSessionService(dataComponentInitializer.getFilmSessionDao(), filmSessionDtoConverter, filmSessionConverter);

        changeLanguageController = new ChangeLanguageController();
        welcomeController = new WelcomeController();
        userController = new UserController(userService);
        filmController = new FilmController(filmService);
        roomController = new RoomController(roomService);
        roomPlaceController = new RoomPlaceController(roomPlaceService, roomService);
        filmSessionController = new FilmSessionController(filmSessionService, filmService, roomService);

        requestResolver = new RequestResolver(this);
    }

    public static WebComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (WebComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new WebComponentInitializer();
                }
            }
        }

        return initializer;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserLoginDtoConverter getUserLoginDtoConverter() {
        return userLoginDtoConverter;
    }

    public RequestResolver getRequestResolver() {
        return requestResolver;
    }

    public UserConverter getUserConverter() {
        return userConverter;
    }

    public FilmConverter getFilmConverter() {
        return filmConverter;
    }

    public FilmDtoConverter getFilmDtoConverter() {
        return filmDtoConverter;
    }

    public RoomDtoConverter getRoomDtoConverter() {
        return roomDtoConverter;
    }

    public RoomPlaceDtoConverter getRoomPlaceDtoConverter() {
        return roomPlaceDtoConverter;
    }

    public FilmSessionConverter getFilmSessionConverter() {
        return filmSessionConverter;
    }

    public FilmSessionDtoConverter getFilmSessionDtoConverter() {
        return filmSessionDtoConverter;
    }

    public WelcomeController getWelcomeController() {
        return welcomeController;
    }

    public UserController getUserController() {
        return userController;
    }

    public ChangeLanguageController getChangeLanguageController() {
        return changeLanguageController;
    }

    public FilmController getFilmController() {
        return filmController;
    }

    public RoomController getRoomController() {
        return roomController;
    }

    public RoomPlaceController getRoomPlaceController() {
        return roomPlaceController;
    }

    public FilmSessionController getFilmSessionController() {
        return filmSessionController;
    }
}
