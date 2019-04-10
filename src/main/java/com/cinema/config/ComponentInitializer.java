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

public class ComponentInitializer {

    private static ComponentInitializer initializer;
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

    private final UserDao userDao;
    private final FilmDao filmDao;
    private final RoomDao roomDao;
    private final RoomPlaceDao roomPlaceDao;
    private final FilmSessionDao filmSessionDao;

    public ComponentInitializer() {

        DataSource dataSource = new DataSource();
        userDao = new UserDao(dataSource);
        filmDao = new FilmDao(dataSource);
        roomDao = new RoomDao(dataSource);
        roomPlaceDao = new RoomPlaceDao(dataSource);
        filmSessionDao = new FilmSessionDao(dataSource);

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

        userService = new UserService(userDao);
        filmService = new FilmService(filmDao, filmConverter, filmDtoConverter);
        roomService = new RoomService(roomDao, roomConverter, roomDtoConverter);
        roomPlaceService = new RoomPlaceService(roomPlaceDao, roomPlaceConverter);
        filmSessionService = new FilmSessionService(filmSessionDao, filmSessionDtoConverter);

        changeLanguageController = new ChangeLanguageController();
        welcomeController = new WelcomeController();
        userController = new UserController(userService);
        filmController = new FilmController(filmService);
        roomController = new RoomController(roomService);
        roomPlaceController = new RoomPlaceController(roomPlaceService, roomService);
        filmSessionController = new FilmSessionController(filmSessionService);

        requestResolver = new RequestResolver(welcomeController,
                userController,
                changeLanguageController,
                filmController,
                roomController,
                roomPlaceController,
                filmSessionController);

    }

    public static ComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (ComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new ComponentInitializer();
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
}
