package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.TicketDto;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;
import com.cinema.service.TicketService;
import com.cinema.service.UserService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

import java.util.List;

public class UserController {

    private final UserService userService;
    private final TicketService ticketService;

    public UserController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
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
            view = new ViewModel("index");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
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
            view = new ViewModel("login");
            view.addParameter("Error", e.getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View createUser(UserDto userDto) {
        View view;
        try {
            userService.createUser(userDto);
            view = new ViewModel("login");
            view.addParameter("Error", "User created!");
        } catch (ServiceException e) {
            view = new ViewModel("registration-form");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);

    }

    private List<TicketDto> showUserHistory(int userId) throws ServiceException {
        return ticketService.receivePurchasedSessionTicketsByUserId(userId);
    }
}
