package com.cinema.controller.filter;

import com.cinema.config.WebComponentInitializer;
import com.cinema.config.UserAuthorization;
import com.cinema.model.converter.dtoConverter.UserLoginDtoConverter;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.enums.Role;
import com.cinema.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationFilter implements Filter {

    private final String loginPageName = "login";
    private final String logoutPageName = "logout";
    private UserLoginDtoConverter userLoginDtoConverter;
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        userLoginDtoConverter = WebComponentInitializer.getInstance().getUserLoginDtoConverter();
        userService = WebComponentInitializer.getInstance().getUserService();

        Map<String, UserAuthorization> usersAuthorization = new HashMap<>();
        ServletContext context = filterConfig.getServletContext();
        context.setAttribute("usersAuthorization", usersAuthorization);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String sessionId = httpRequest.getSession().getId();
        Map<String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) httpRequest.getServletContext().getAttribute("usersAuthorization");

        authorizationUser(httpRequest, usersAuthorization, sessionId);
        logout(httpRequest, usersAuthorization, sessionId);

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private void authorizationUser(HttpServletRequest httpRequest, Map<String, UserAuthorization> usersAuthorization, String sessionId) {

        String email = httpRequest.getParameter("email");

        if (httpRequest.getRequestURI().contains(loginPageName) && (email != null)) {

            String foreignUserSession = userSessionOtherSession(email, usersAuthorization, sessionId);

            if (foreignUserSession != null) {
                removeUserAuthorization(usersAuthorization, foreignUserSession);
            }
            createUserAuthorization(httpRequest, usersAuthorization, email);
        }

    }

    private void logout(HttpServletRequest httpRequest, Map<String, UserAuthorization> usersAuthorization, String sessionId) {
        if (httpRequest.getRequestURI().contains(logoutPageName)) {
            removeUserAuthorization(usersAuthorization, sessionId);
        }
    }

    private String userSessionOtherSession(String email, Map<String, UserAuthorization> usersAuthorization, String sessionId) {
        for (Map.Entry<String, UserAuthorization> stringUserAuthorizationEntry : usersAuthorization.entrySet()) {
            String key = stringUserAuthorizationEntry.getKey();
            UserAuthorization value = stringUserAuthorizationEntry.getValue();
            if (value.getEmail().equals(email) && !sessionId.equals(key)) {
                return key;
            }
        }
        return null;
    }

    private void removeUserAuthorization(Map<String, UserAuthorization> usersAuthorization, String UserSession) {
        usersAuthorization.remove(UserSession);
    }

    private void createUserAuthorization(HttpServletRequest httpRequest,
                                         Map<String, UserAuthorization> usersAuthorization, String email) {

        UserDto userDto = userLoginDtoConverter.convert(httpRequest);

        Role role = userService.receiveUserRole(userDto);
        if (!role.equals(Role.UNKNOWN)) {
            UserAuthorization userAuthorization = new UserAuthorization();
            userAuthorization.setEmail(email);
            userAuthorization.setRole(role);
            usersAuthorization.put(httpRequest.getSession().getId(), userAuthorization);
        }
    }

    @Override
    public void destroy() {

    }
}
