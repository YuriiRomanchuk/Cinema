package com.cinema.model.statistics;

import com.cinema.model.entity.Film;

public class FilmSale {

    private Film film;
    private int numberOfTickets;

    public Film getFilm() {
        return film;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
