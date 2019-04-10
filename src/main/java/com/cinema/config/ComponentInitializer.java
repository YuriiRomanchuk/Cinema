package com.cinema.config;

import com.cinema.controller.*;
import com.cinema.model.converter.dtoConverter.RoomPlaceDtoConverter;
import com.cinema.model.converter.entityConverter.FilmConverter;
import com.cinema.model.converter.entityConverter.RoomConverter;
import com.cinema.model.converter.UserConverter;
import com.cinema.model.converter.dtoConverter.FilmDtoConverter;
import com.cinema.model.converter.dtoConverter.RoomDtoConverter;
import com.cinema.model.converter.dtoConverter.UserLoginDtoConverter;
import com.cinema.model.converter.entityConverter.RoomPlaceConverter;
import com.cinema.model.dao.*;
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

    private final FilmService filmService;
    private final UserService userService;
    private final RoomService roomService;
    private final RoomPlaceService roomPlaceService;

    private final UserLoginDtoConverter userLoginDtoConverter;
    private final FilmDtoConverter filmDtoConverter;
    private final RoomDtoConverter roomDtoConverter;
    private final RoomPlaceDtoConverter roomPlaceDtoConverter;
    private final UserConverter userConverter;
    private final FilmConverter filmConverter;
    private final RoomConverter roomConverter;
    private final RoomPlaceConverter roomPlaceConverter;

    private final UserDao userDao;
    private final FilmDao filmDao;
    private final RoomDao roomDao;
    private final RoomPlaceDao roomPlaceDao;

    public ComponentInitializer() {

        DataSource dataSource = new DataSource();
        userDao = new UserDao(dataSource);
        filmDao = new FilmDao(dataSource);
        roomDao = new RoomDao(dataSource);
        roomPlaceDao = new RoomPlaceDao(dataSource);

        userLoginDtoConverter = new UserLoginDtoConverter();
        userConverter = new UserConverter();
        filmConverter = new FilmConverter();
        roomConverter = new RoomConverter();
        roomPlaceConverter = new RoomPlaceConverter(roomConverter);
        filmDtoConverter = new FilmDtoConverter();
        roomDtoConverter = new RoomDtoConverter();
        roomPlaceDtoConverter = new RoomPlaceDtoConverter(roomDtoConverter);

        userService = new UserService(userDao);
        filmService = new FilmService(filmDao, filmConverter, filmDtoConverter);
        roomService = new RoomService(roomDao, roomConverter, roomDtoConverter);
        roomPlaceService = new RoomPlaceService(roomPlaceDao, roomPlaceConverter);

        changeLanguageController = new ChangeLanguageController();
        welcomeController = new WelcomeController();
        userController = new UserController(userService);
        filmController = new FilmController(filmService);
        roomController = new RoomController(roomService);
        roomPlaceController = new RoomPlaceController(roomPlaceService, roomService);


        requestResolver = new RequestResolver(welcomeController,
                userController,
                changeLanguageController,
                filmController,
                roomController,
                roomPlaceController);

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
