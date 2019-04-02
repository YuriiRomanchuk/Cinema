package com.cinema.model.converter;

import com.cinema.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserDtoConverter implements Converter<UserDto> {
    @Override
    public UserDto convert(HttpServletRequest request) {

        UserDto userDto = new UserDto();
        userDto.setEmail(request.getParameter("email"));
        userDto.setPassword(request.getParameter("password"));
        return userDto;
    }
}
