package com.cinema.service;

import com.cinema.exception.ModelException;
import com.cinema.model.dao.UserDao;
import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User loginUser(UserDto userDto) throws ModelException {
        try {
            return userDao.loginUser(userDto);
        } catch (Exception e) {
            throw new ModelException("Login or password is not correct", e);
        }
    }

    public void createUser(User user) throws ModelException {
        try {
            userDao.createUser(user);
        } catch (Exception e) {
            throw new ModelException("Registration failed", e);
        }

    }
}
