package com.cinema.controller;

import com.cinema.model.entity.enums.Role;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionController {

    private HttpServletRequest request;

    public void setUserRoleInSession(Role role, String email, Integer id) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("email", email);
        session.setAttribute("role", role);
        session.setAttribute("id", id);
    }

    public boolean checkUserIsLogged() {
        if (Role.UNKNOWN.equals(request.getSession().getAttribute("role"))
                || request.getSession().getAttribute("role") == null) {
            return false;
        }

        return true;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
