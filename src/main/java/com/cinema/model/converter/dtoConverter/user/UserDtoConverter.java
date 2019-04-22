package com.cinema.model.converter.dtoConverter.user;

import com.cinema.model.converter.Converter;
import com.cinema.model.converter.dtoConverter.FilmDtoConverter;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserDtoConverter implements Converter<HttpServletRequest, UserDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public UserDto convert(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setLogin(request.getParameter("login"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setLastName(request.getParameter("lastName"));
        userDto.setFirstName(request.getParameter("firstName"));
        userDto.setMiddleName(request.getParameter("middleName"));
        userDto.setEmail(request.getParameter("email"));
        userDto.setPhone(request.getParameter("phone"));
        LOGGER.debug("User dto is converted from request!");
        return userDto;
    }

    public UserDto convertFromUserEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setRole(user.getRole());
        LOGGER.debug("User dto is converted from entity!");
        return userDto;
    }

    public UserDto convertFromRequestForUserId(HttpServletRequest request) {
        int userId = (Integer) request.getSession().getAttribute("user_id");
        LOGGER.debug("User dto is converted from id!");
        return convertForUserId(userId);
    }

    public UserDto convertForUserId(int userId) {
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        LOGGER.debug("User dto is converted from id!");
        return userDto;
    }
}


