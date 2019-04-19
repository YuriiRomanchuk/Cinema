package com.cinema.model.dao;

import com.cinema.model.converter.resultSetConverter.UserResultSetConverter;
import com.cinema.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserDao implements GenericDao<User> {

    private final DataSource dataSource;
    private final UserResultSetConverter userResultSetConverter;

    public UserDao(DataSource dataSource, UserResultSetConverter userResultSetConverter) {
        this.dataSource = dataSource;
        this.userResultSetConverter = userResultSetConverter;
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
        return dataSource.receiveRecords("select *, users.id as user_id  from users",
                resultSet -> userResultSetConverter.convert(resultSet),
                preparedStatement -> {
                });
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return dataSource.receiveFirstRecord("select *, users.id as user_id from users where email = ? and password = ?",
                resultSet -> userResultSetConverter.convert(resultSet),
                preparedStatement ->
                {
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);
                });
    }

    public void createUser(User user) {

        final String query = "insert into users (first_name, last_name, middle_name, login, password, email, phone, role) values(?, ?, ?, ?, ?, ?, ?, ?)";

        dataSource.update(query, ps -> {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getMiddleName());
            ps.setString(4, user.getLogin());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setInt(7, user.getPhone());
            ps.setString(8, String.valueOf(user.getRole()));
        }, r -> user.setId(r.getInt(1)));
    }
}
