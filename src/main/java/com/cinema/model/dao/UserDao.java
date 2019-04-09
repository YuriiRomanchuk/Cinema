package com.cinema.model.dao;

import com.cinema.model.entity.User;
import com.cinema.model.entity.enums.Role;

import java.sql.SQLException;
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

    public User findUserByEmailAndPassword(String email, String password) {
        return dataSource.receiveFirstRecord("select * from users where email = ? and password = ?",
                userConverter,
                preparedStatement ->
                {
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);
                }).orElse(null);
    }

    public Role findUserRole(String email, String password) {
        User user = findUserByEmailAndPassword(email, password);
        return user != null ? user.getRole() : null;
    }


    private void receiveConverter() {
        userConverter = rs -> {
            User user = new User();
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setMiddleName(rs.getString("middle_name"));
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getInt("phone"));
            user.setRole(Role.valueOf(rs.getString("role")));
            return user;
        };

    }

    public void createUser(User user) {

        final String query = "insert into users (first_name, last_name, middle_name, login, password, email, phone, role) values(?, ?, ?, ?, ?, ?, ?, ?)";

        dataSource.implementWrite(query, ps -> {
            try {
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getMiddleName());
                ps.setString(4, user.getLogin());
                ps.setString(5, user.getPassword());
                ps.setString(6, user.getEmail());
                ps.setInt(7, user.getPhone());
                ps.setString(8, String.valueOf(user.getRole()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }, r -> user.setId(r.getInt(1)));
    }
}
