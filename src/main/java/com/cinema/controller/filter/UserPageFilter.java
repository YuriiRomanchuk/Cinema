package com.cinema.controller.filter;

import com.cinema.config.UserAuthorization;
import com.cinema.model.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UserPageFilter implements Filter {

    private List<String> forbiddenPagesUser = new ArrayList<>();
    private List<String> forbiddenPagesAdmin = new ArrayList<>();
    private List<String> forbiddenPagesUnknown = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        forbiddenPagesUser.add("admin-personal-area");
        forbiddenPagesUser.add("login");
        forbiddenPagesUser.add("registration-form");
        forbiddenPagesUser.add("error");
        forbiddenPagesUser.add("admin-add-film");

        forbiddenPagesUnknown.add("admin-personal-area");
        forbiddenPagesUnknown.add("user-personal-area");
        forbiddenPagesUnknown.add("error");
        forbiddenPagesUnknown.add("admin-add-film");

        forbiddenPagesAdmin.add("user-personal-area");
        forbiddenPagesAdmin.add("error");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String sessionId = httpRequest.getSession().getId();
        Map<String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) httpRequest.getServletContext().getAttribute("usersAuthorization");

        UserAuthorization userAuthorization = usersAuthorization.get(sessionId);

        boolean redirect = false;

        List<Function<HttpServletRequest, Boolean>> functions = receiveRulesByPage(userAuthorization);

        for (Function<HttpServletRequest, Boolean> function : functions) {
            redirect = function.apply(httpRequest);
            if (redirect || userAuthorization == null) {
                break;
            }
        }

        if (!redirect) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(httpRequest.getContextPath() + "/main/index");
        }

    }

    private List<Function<HttpServletRequest, Boolean>> receiveRulesByPage(UserAuthorization userAuthorization) {
        List<Function<HttpServletRequest, Boolean>> rules = new ArrayList<>();
        rules.add(request -> (userAuthorization == null) && pageIsLockedByRole(request, forbiddenPagesUnknown));
        rules.add(request -> (userAuthorization.getRole().equals(Role.USER)) && pageIsLockedByRole(request, forbiddenPagesUser));
        rules.add(request -> (userAuthorization.getRole().equals(Role.ADMIN)) && pageIsLockedByRole(request, forbiddenPagesAdmin));

        return rules;
    }

    private boolean pageIsLockedByRole(HttpServletRequest httpRequest, List<String> forbiddenPages) {
        for (String page : forbiddenPages) {
            if (httpRequest.getRequestURI().contains(page)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
