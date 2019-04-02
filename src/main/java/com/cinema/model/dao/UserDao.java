package com.cinema.model.dao;

import com.cinema.model.dto.UserDto;
import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

import java.util.List;

public class UserDao implements GenericDao<User> {

    private DataSource dataSource;
    private DataSource.SqlFunction<User> userConverter;


    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
        receiveConverter();
    }

    @Override
    public void insert(User entity) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    public User loginUser(UserDto userDto) {
        return dataSource.receiveFirstRecord("select * from users where login = ? and password = ?",
                userConverter,
                preparedStatement ->
                {
                    preparedStatement.setString(1, userDto.getNickname());
                    preparedStatement.setString(2, userDto.getPassword());
                }).orElse(null);
    }


    private void receiveConverter() {
        userConverter = rs -> {
            User user = new User();
            user.setFirst_name(rs.getString("first_name"));
            user.setSecond_name(rs.getString("second_name"));
            user.setMiddle_name(rs.getString("middle_name"));
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getInt("phone"));
            user.setRole(Role.valueOf(rs.getString("role")));
            return user;
        };

    }

}
