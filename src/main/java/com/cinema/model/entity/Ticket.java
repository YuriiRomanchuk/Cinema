package com.cinema.model.entity;

public class Ticket {

    private int id;
    private User user;
    private RoomPlace roomPlace;
    private FilmSession filmSession;


    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public RoomPlace getRoomPlace() {
        return roomPlace;
    }

    public FilmSession getFilmSession() {
        return filmSession;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoomPlace(RoomPlace roomPlace) {
        this.roomPlace = roomPlace;
    }

    public void setFilmSession(FilmSession filmSession) {
        this.filmSession = filmSession;
    }
}
