package com.cinema.model.entity;

import java.util.Date;

public class FilmSession {

    private int id;
    private Film film;
    private Room room;
    private Date date;


    public int getId() {
        return id;
    }

    public Film getFilm() {
        return film;
    }

    public Room getRoom() {
        return room;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
