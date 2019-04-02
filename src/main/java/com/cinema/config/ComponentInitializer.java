package com.cinema.config;

import com.cinema.controller.UserController;
import com.cinema.controller.WelcomeController;
import com.cinema.model.dao.DataSource;
import com.cinema.model.dao.UserDao;
import com.cinema.service.UserService;
import com.cinema.servlet.RequestResolver;

public class ComponentInitializer {

    private static ComponentInitializer initializer;
    private final RequestResolver requestResolver;
    private final WelcomeController welcomeController;

    public ComponentInitializer() {

        welcomeController = new WelcomeController();

        DataSource dataSource = new DataSource();
        UserDao userDao = new UserDao(dataSource);
        UserService userService = new UserService(userDao);
        UserController userController = new UserController(userService);

        requestResolver = new RequestResolver(welcomeController, userController);

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
