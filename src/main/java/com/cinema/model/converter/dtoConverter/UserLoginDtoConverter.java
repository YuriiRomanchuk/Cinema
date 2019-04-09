package com.cinema.model.converter.dtoConverter;

import com.cinema.model.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserLoginDtoConverter implements DtoConverter<UserDto> {
    @Override
    public UserDto convertFromHttpRequest(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setEmail(request.getParameter("email"));
        userDto.setPassword(request.getParameter("password"));
        return userDto;
    }
}
