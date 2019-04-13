package com.cinema.model.converter.utility;

import com.cinema.model.entity.*;
import com.cinema.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoConverter {

    public static Room convertResultSetToRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setName(resultSet.getString("room_name"));
        room.setNameEnglish(resultSet.getString("room_name_english"));
        room.setId(resultSet.getInt("room_id"));
        return room;
    }

    public static Film convertResultSetToFilm(ResultSet resultSet) throws SQLException {
        Film film = new Film();
        film.setName(resultSet.getString("name"));
        film.setNameEnglish(resultSet.getString("name_english"));
        film.setId(resultSet.getInt("film_id"));
        film.setDescription(resultSet.getString("description"));
        film.setReleaseDate(resultSet.getTimestamp("release_date"));
        film.setDescriptionEnglish(resultSet.getString("description_english"));
        film.setRunningTime(resultSet.getInt("running_time"));
        return film;
    }

    public static FilmSession convertResultSetToFilmSession(ResultSet resultSet) throws SQLException {
        FilmSession filmSession = new FilmSession();
        filmSession.setId(resultSet.getInt("session_id"));
        filmSession.setDate(resultSet.getTimestamp("session_date"));
        filmSession.setFilm(DaoConverter.convertResultSetToFilm(resultSet));
        filmSession.setRoom(DaoConverter.convertResultSetToRoom(resultSet));
        return filmSession;
    }

    public static User convertResultSetToUser(ResultSet resultSet) throws SQLException{
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

    public static RoomPlace convertResultSetToRoomPlace(ResultSet resultSet) throws SQLException{
        RoomPlace roomPlace = new RoomPlace();
        roomPlace.setId(resultSet.getInt("place_id"));
        roomPlace.setRow(resultSet.getInt("place_row"));
        roomPlace.setPlace(resultSet.getInt("place_place"));
        roomPlace.setRoom(DaoConverter.convertResultSetToRoom(resultSet));
        return roomPlace;
    }
}
