package com.cinema.model.converter.entityConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserConverter implements Converter<UserDto, User> {

    private static final Logger LOGGER = LogManager.getLogger(UserConverter.class);
    @Override
    public User convert(UserDto userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        LOGGER.debug("User is converted from user dto!");
        return user;
    }
}
