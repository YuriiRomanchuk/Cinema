package com.cinema.config;

import com.cinema.controller.ChangeLanguageController;
import com.cinema.controller.FilmController;
import com.cinema.controller.UserController;
import com.cinema.controller.WelcomeController;
import com.cinema.model.converter.EntityConverter.FilmConverter;
import com.cinema.model.converter.UserConverter;
import com.cinema.model.converter.dtoConverter.FilmDtoConverter;
import com.cinema.model.converter.dtoConverter.UserLoginDtoConverter;
import com.cinema.model.dao.DataSource;
import com.cinema.model.dao.FilmDao;
import com.cinema.model.dao.UserDao;
import com.cinema.service.FilmService;
import com.cinema.service.UserService;
import com.cinema.servlet.RequestResolver;

public class ComponentInitializer {

    private static ComponentInitializer initializer;
    private final RequestResolver requestResolver;

    private final WelcomeController welcomeController;
    private final UserController userController;
    private final ChangeLanguageController changeLanguageController;
    private final FilmController filmController;

    private final FilmService filmService;
    private final UserService userService;

    private final UserLoginDtoConverter userLoginDtoConverter;
    private final FilmDtoConverter filmDtoConverter;
    private final UserConverter userConverter;
    private final FilmConverter filmConverter;

    private final UserDao userDao;
    private final FilmDao filmDao;

    public ComponentInitializer() {

        DataSource dataSource = new DataSource();
        userDao = new UserDao(dataSource);
        filmDao = new FilmDao(dataSource);

        changeLanguageController = new ChangeLanguageController();
        userService = new UserService(userDao);

        userLoginDtoConverter = new UserLoginDtoConverter();

        userConverter = new UserConverter();
        filmConverter = new FilmConverter();
        filmDtoConverter = new FilmDtoConverter();

        welcomeController = new WelcomeController();
        userController = new UserController(userService);
        filmService = new FilmService(filmDao, filmConverter);
        filmController = new FilmController(filmService);


        requestResolver = new RequestResolver(welcomeController,
                userController,
                changeLanguageController,
                filmController);

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
}
