package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.TicketDto;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;
import com.cinema.service.FilmSessionService;
import com.cinema.service.TicketService;
import com.cinema.service.UserService;
import com.cinema.validator.UserLoginValidator;
import com.cinema.validator.UserRegistrationDataValidator;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    private final UserService userService;
    private final TicketService ticketService;
    private final FilmSessionService filmSessionService;
    private final UserRegistrationDataValidator userRegistrationDataValidator;
    private final UserLoginValidator userLoginValidator;

    public UserController(UserService userService,
                          TicketService ticketService,
                          FilmSessionService filmSessionService,
                          UserRegistrationDataValidator userRegistrationDataValidator,
                          UserLoginValidator userLoginValidator) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.filmSessionService = filmSessionService;
        this.userRegistrationDataValidator = userRegistrationDataValidator;
        this.userLoginValidator = userLoginValidator;
    }

    public View showRegistrationPage() {
        return new ViewModel("WEB-INF/jsp/registration-form.jsp");
    }

    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/jsp/login.jsp");
    }

    public View showAdminPersonalArea(Date currentDate) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-personal-area.jsp");
            view.addParameter("filmSaleDto", filmSessionService.receiveFilmSalesByDate(currentDate));
            LOGGER.debug("show admin personal area");
        } catch (ServiceException e) {
            view = receiveViewModel("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("Admin personal area is not shown!" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return view;
    }

    public View showUserPersonalArea(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-personal-area.jsp");
            view.addParameter("userTicketsHistory", showUserHistory(userDto.getId()));
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View logout() {
        LOGGER.debug("User logout");
        return new RedirectViewModel(new ViewModel("index"));
    }

    public View loginUser(UserDto userDto) {
        View view;
        try {
            view = validateLoginUser(userDto);
            LOGGER.debug("User login");
        } catch (ServiceException e) {
            view = receiveViewModel("login", e.getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View createUser(UserDto userDto) {
        View view;
        try {
            view = validateRegistrationUser(userDto);
            LOGGER.debug("User create");
        } catch (ServiceException e) {
            view = receiveViewModel("registration-form", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("User is not created" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    private View validateLoginUser(UserDto userDto) throws ServiceException {
        View view;
        String invalidateFields = userLoginValidator.validate(userDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("login", invalidateFields);
        } else {
            User user = userService.loginUser(userDto);
            view = receiveViewModel(user.getRole().equals(Role.ADMIN) ? "admin-personal-area" : "user-personal-area", "");
        }
        return view;
    }

    private View validateRegistrationUser(UserDto userDto) throws ServiceException {
        View view;
        String invalidateFields = userRegistrationDataValidator.validate(userDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("registration-form", invalidateFields);
            view.addParameter("userDto", userDto);
        } else {
            userService.createUser(userDto);
            view = receiveViewModel("login", "User created!");
        }
        return view;
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        LOGGER.debug(error);
        return view;
    }

    private List<TicketDto> showUserHistory(int userId) throws ServiceException {
        return ticketService.receivePurchasedSessionTicketsByUserId(userId);
    }
}
