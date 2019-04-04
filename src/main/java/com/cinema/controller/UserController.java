package com.cinema.controller;

import com.cinema.exception.ModelException;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;
import com.cinema.service.UserService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public View showRegistrationPage() {
        return new ViewModel("WEB-INF/registration-form.jsp");
    }

    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/login.jsp");
    }

    public View showAdminPersonalArea() {
        return new ViewModel("WEB-INF/admin/admin-personal-area.jsp");
    }

    public View showUserPersonalArea() {
        return new ViewModel("WEB-INF/user/user-personal-area.jsp");
    }

    public View logout() {
        return new ViewModel("WEB-INF/index.jsp");
    }

    public View loginUser(UserDto userDto) {

        View view;
        try {
            User user = userService.loginUser(userDto);
            view = new ViewModel(user.getRole().equals(Role.ADMIN) ? "admin-personal-area" : "user-personal-area");
        } catch (ModelException e) {
            view = new ViewModel("login");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View createUser(User user) {
        View view;
        try {
            userService.createUser(user);
            view = new ViewModel("WEB-INF/login");
        } catch (ModelException e) {
            view = new ViewModel("registration-form");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);

    }

}
