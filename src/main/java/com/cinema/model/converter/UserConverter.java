package com.cinema.model.converter;

import com.cinema.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class UserConverter implements Converter<User> {

    @Override
    public User convert(HttpServletRequest request) {
        return new User();
    }
}
