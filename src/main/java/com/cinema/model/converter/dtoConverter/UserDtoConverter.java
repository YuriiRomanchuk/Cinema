package com.cinema.model.converter.dtoConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

import javax.servlet.http.HttpServletRequest;

public class UserDtoConverter implements Converter<HttpServletRequest, UserDto> {
    @Override
    public UserDto convert(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setLogin(request.getParameter("login"));
        userDto.setPassword(request.getParameter("password"));
        userDto.setLastName(request.getParameter("lastName"));
        userDto.setFirstName(request.getParameter("firstName"));
        userDto.setMiddleName(request.getParameter("middleName"));
        userDto.setEmail(request.getParameter("email"));
        userDto.setPhone(Integer.valueOf(request.getParameter("phone")));
        userDto.setRole(Role.USER);
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
        return userDto;
    }
}


