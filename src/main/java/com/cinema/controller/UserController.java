package com.cinema.controller;

import com.cinema.service.UserService;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/login.jsp");
    }

}
