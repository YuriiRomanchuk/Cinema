package com.cinema.model.converter;

import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;

public class UserConverter implements Converter<User> {

    @Override
    public User convert(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setRole(Role.USER);
        user.setSecond_name(request.getParameter("second_name"));
        user.setFirst_name(request.getParameter("first_name"));
        user.setMiddle_name(request.getParameter("middle_name"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(Integer.valueOf(request.getParameter("phone")));
        return user;
    }
}
