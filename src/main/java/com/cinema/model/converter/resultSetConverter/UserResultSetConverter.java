package com.cinema.model.converter.resultSetConverter;

import com.cinema.model.converter.Converter;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetConverter implements Converter<ResultSet, User> {

    @Override
    public User convert(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setMiddleName(resultSet.getString("middle_name"));
        user.setId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setPhone(resultSet.getInt("phone"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        return user;
    }
}
