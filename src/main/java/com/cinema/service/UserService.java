package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.converter.entityConverter.UserConverter;
import com.cinema.model.dao.UserDao;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

import java.util.List;

public class UserService {

    private final UserDao userDao;
    private final UserConverter userConverter;

    public UserService(UserDao userDao, UserConverter userConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
    }

    public User loginUser(UserDto userDto) throws ServiceException {
        try {
            return userDao.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword()).orElseThrow(IllegalArgumentException::new);
        } catch (Exception e) {
            throw new ServiceException("Login or password is not correct", e);
        }
    }

    public void createUser(UserDto userDto) throws ServiceException {
        try {
            User user = userConverter.convert(userDto);
            user.setRole(receiveRoleForUser());
            userDao.createUser(user);
        } catch (Exception e) {
            throw new ServiceException("Registration failed", e);
        }
    }

    private Role receiveRoleForUser() {
        List<User> allUsers = userDao.findAll();
        return allUsers.size() > 0 ? Role.USER : Role.ADMIN;
    }

    public Role receiveUserRole(UserDto userDto) {
        return userDao.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword()).map(User::getRole).orElse(Role.UNKNOWN);
    }

    public int receiveUserId(UserDto userDto) {
        return userDao.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword()).map(User::getId).orElse(-1);
    }
}
