package com.cinema.model.converter;

import com.cinema.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserDtoConverter implements Converter<UserDto> {
    @Override
    public UserDto convert(HttpServletRequest request) {

        UserDto userDto = new UserDto();
        userDto.setNickname(request.getParameter("login"));
        userDto.setPassword(request.getParameter("password"));
        return userDto;
    }
}
