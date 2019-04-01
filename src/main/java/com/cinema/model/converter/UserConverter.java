package com.cinema.model.converter;

import com.cinema.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class UserConverter implements Converter {

    @Override
    public User convertToObject(HttpServletRequest request) {
        return new User();
    }
}
