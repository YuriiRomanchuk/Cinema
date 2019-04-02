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

    public View showUserLoginPage(SessionController sessionController) {
        sessionController.setUserRoleInSession(Role.UNKNOWN, null, null);
        return new ViewModel("WEB-INF/login.jsp");
    }

    public View showAdminPersonalArea(SessionController sessionController) {
        if (sessionController.checkUserIsLogged()) {
            return new ViewModel("WEB-INF/admin/admin-personal-area.jsp");
        } else {
            return new ViewModel("WEB-INF/index.jsp");
        }
    }

    public View showUserPersonalArea(SessionController sessionController) {
        if (sessionController.checkUserIsLogged()) {
            return new ViewModel("WEB-INF/user/user-personal-area.jsp");
        } else {
            return new ViewModel("WEB-INF/index.jsp");
        }
    }

    public View Logout(SessionController sessionController) {
        sessionController.setUserRoleInSession(Role.UNKNOWN, null, null);
        return new ViewModel("WEB-INF/index.jsp");
    }

    public View loginUser(UserDto userDto, SessionController sessionController) {

        View view;
        try {
            User user = userService.loginUser(userDto);
            sessionController.setUserRoleInSession(user.getRole(), user.getEmail(), user.getId());
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
