package com.cinema.model.converter;

import com.cinema.model.converter.dtoConverter.DtoConverter;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;

public class UserConverter implements DtoConverter<User> {

    @Override
    public User convertFromHttpRequest(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setRole(Role.USER);
        user.setLastName(request.getParameter("lastName"));
        user.setFirstName(request.getParameter("firstName"));
        user.setMiddleName(request.getParameter("middleName"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(Integer.valueOf(request.getParameter("phone")));
        return user;
    }
}
