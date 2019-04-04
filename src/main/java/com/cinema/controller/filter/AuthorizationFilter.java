package com.cinema.controller.filter;

import com.cinema.config.ComponentInitializer;
import com.cinema.config.UserAuthorization;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.enums.Role;
import com.cinema.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorizationFilter implements Filter {

    private List<String> bannedPagesForUnknow = new ArrayList<>();
    private final String loginPageName = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

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

        if (httpRequest.getRequestURI().contains(loginPageName)) {

            String email = httpRequest.getParameter("email");

            if (email != null) {

                String foreignUserSession = userSessionOtherSession(email, usersAuthorization, sessionId);

                if (foreignUserSession != null) {
                    removeUserAuthorization(httpRequest, usersAuthorization, foreignUserSession);
                }
                createUserAuthorization(httpRequest, usersAuthorization, email);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
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

    private void removeUserAuthorization(HttpServletRequest httpRequest, Map<String, UserAuthorization> usersAuthorization, String foreignUserSession ) {
        usersAuthorization.remove(foreignUserSession);
    }

    private void createUserAuthorization(HttpServletRequest httpRequest,
                                         Map<String, UserAuthorization> usersAuthorization, String email) {

        UserService userService = ComponentInitializer.getInstance().getUserService();
        UserDto userDto = ComponentInitializer.getInstance().getUserDtoConverter().convert(httpRequest);

        Role role = userService.receiveUserRole(userDto);
        if (role != null) {
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
