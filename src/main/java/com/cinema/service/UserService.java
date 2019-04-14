package com.cinema.service;

import com.cinema.exception.ServiceException;
import com.cinema.model.dao.UserDao;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User loginUser(UserDto userDto) throws ServiceException {
        try {
            return userDao.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        } catch (Exception e) {
            throw new ServiceException("Login or password is not correct", e);
        }
    }

    public void createUser(User user) throws ServiceException {
        try {
            userDao.createUser(user);
        } catch (Exception e) {
            throw new ServiceException("Registration failed", e);
        }
    }

    public Role receiveUserRole(UserDto userDto) {
        User user = userDao.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return user != null ? user.getRole() : Role.UNKNOWN;
    }

    public int receiveUserId(UserDto userDto) {
        User user = userDao.findUserByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return user.getId();
    }
}
