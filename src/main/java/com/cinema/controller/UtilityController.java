package com.cinema.controller;

import com.cinema.model.entity.enums.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UtilityController {

    private HttpServletRequest request;

    public void setUserRoleInSession(Role role, String login, Integer id) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("login", login);
        session.setAttribute("role", role);
        session.setAttribute("id", id);
    }

    public boolean checkUserIsLogged() {
        if (request.getSession().getAttribute("role") != null) {
            return true;
        }

        return false;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
