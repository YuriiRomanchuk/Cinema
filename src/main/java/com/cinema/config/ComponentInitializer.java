package com.cinema.config;

import com.cinema.controller.UserController;
import com.cinema.controller.WelcomeController;
import com.cinema.model.converter.UserConverter;
import com.cinema.model.converter.UserDtoConverter;
import com.cinema.model.dao.DataSource;
import com.cinema.model.dao.UserDao;
import com.cinema.service.UserService;
import com.cinema.servlet.RequestResolver;

public class ComponentInitializer {

    private static ComponentInitializer initializer;
    private final RequestResolver requestResolver;
    private final WelcomeController welcomeController;
    private final UserController userController;

    public UserDtoConverter getUserDtoConverter() {
        return userDtoConverter;
    }

    private final UserDtoConverter userDtoConverter;
    private final UserConverter userConverter;

    public UserService getUserService() {
        return userService;
    }

    private final UserService userService;

    public ComponentInitializer() {

        DataSource dataSource = new DataSource();
        UserDao userDao = new UserDao(dataSource);
        userService = new UserService(userDao);

        userDtoConverter = new UserDtoConverter();
        userConverter = new UserConverter();

        welcomeController = new WelcomeController();
        userController = new UserController(userService);

        requestResolver = new RequestResolver(welcomeController,
                userController,
                userDtoConverter,
                userConverter);

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

    public RequestResolver getRequestResolver() {
        return requestResolver;
    }


}
