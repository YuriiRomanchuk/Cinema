package com.cinema.config;

import com.cinema.controller.UserController;
import com.cinema.model.dao.DataSource;
import com.cinema.model.dao.UserDao;
import com.cinema.service.UserService;
import com.cinema.servlet.RequestResolver;

public class ComponentInitializer {

    private static ComponentInitializer initializer;
    private final RequestResolver requestResolver;

    private ComponentInitializer() {

        DataSource dataSource = new DataSource();
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        UserController userController = new UserController(userService);

        requestResolver = new RequestResolver();

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
}
