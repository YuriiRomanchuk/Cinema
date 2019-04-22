package com.cinema.model.converter.dtoConverter.user;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserLoginDtoConverter implements Converter<HttpServletRequest, UserDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public UserDto convert(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setEmail(request.getParameter("email"));
        userDto.setPassword(request.getParameter("password"));
        LOGGER.debug("User login dto is converted from request!");
        return userDto;
    }
}
