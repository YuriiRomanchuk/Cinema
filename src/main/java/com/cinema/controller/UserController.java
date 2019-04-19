package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.TicketDto;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;
import com.cinema.service.TicketService;
import com.cinema.service.UserService;
import com.cinema.validator.UserRegistrationDataValidator;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

import java.util.List;

public class UserController {

    private final UserService userService;
    private final TicketService ticketService;
    private final UserRegistrationDataValidator userRegistrationDataValidator;

    public UserController(UserService userService, TicketService ticketService, UserRegistrationDataValidator userRegistrationDataValidator) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.userRegistrationDataValidator = userRegistrationDataValidator;
    }

    public View showRegistrationPage() {
        return new ViewModel("WEB-INF/jsp/registration-form.jsp");
    }

    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/jsp/login.jsp");
    }

    public View showAdminPersonalArea() {
        return new ViewModel("WEB-INF/jsp/admin/admin-personal-area.jsp");
    }

    public View showUserPersonalArea(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-personal-area.jsp");
            view.addParameter("userTicketsHistory", showUserHistory(userDto.getId()));
            return view;
        } catch (ServiceException e) {
            view = receiveModelWithMessage("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View logout() {
        return new RedirectViewModel(new ViewModel("index"));
    }

    public View loginUser(UserDto userDto) {
        View view;
        try {
            User user = userService.loginUser(userDto);
            view = new ViewModel(user.getRole().equals(Role.ADMIN) ? "admin-personal-area" : "user-personal-area");
        } catch (ServiceException e) {
            view = receiveModelWithMessage("login", e.getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View createUser(UserDto userDto) {
        View view;
        try {
            view = validateRegistrationUser(userDto);
        } catch (ServiceException e) {
            view = receiveModelWithMessage("registration-form", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    private View validateRegistrationUser(UserDto userDto) throws ServiceException {
        View view;
        String invalidateFields = userRegistrationDataValidator.validate(userDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveModelWithMessage("registration-form", invalidateFields);
            view.addParameter("userDto", userDto);
        } else {
            userService.createUser(userDto);
            view = receiveModelWithMessage("login", "User created!");
        }
        return view;
    }

    private View receiveModelWithMessage(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        return view;
    }

    private List<TicketDto> showUserHistory(int userId) throws ServiceException {
        return ticketService.receivePurchasedSessionTicketsByUserId(userId);
    }
}
