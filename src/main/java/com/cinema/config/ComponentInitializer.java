package com.cinema.config;

import com.cinema.controller.WelcomeController;
import com.cinema.servlet.RequestResolver;

public class ComponentInitializer {

    private static ComponentInitializer initializer;
    private final RequestResolver requestResolver;
    private final WelcomeController welcomeController;

    public ComponentInitializer() {

        welcomeController = new WelcomeController();

       /* DataSource dataSource = new DataSource();
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        UserController userController = new UserController(userService);*/

        requestResolver = new RequestResolver(welcomeController);

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
