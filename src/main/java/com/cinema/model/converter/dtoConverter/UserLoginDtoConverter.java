package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserLoginDtoConverter implements Converter<HttpServletRequest, UserDto> {

    @Override
    public UserDto convert(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setEmail(request.getParameter("email"));
        userDto.setPassword(request.getParameter("password"));
        return userDto;
    }
}
