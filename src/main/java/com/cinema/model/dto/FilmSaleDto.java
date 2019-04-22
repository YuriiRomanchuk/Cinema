package com.cinema.model.dto;

public class FilmSaleDto {

    private int filmId;
    private String filmName;
    private String filmNameEnglish;
    private int numberOfTickets;
    private String ReleaseDate;

    public int getFilmId() {
        return filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getFilmNameEnglish() {
        return filmNameEnglish;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setFilmNameEnglish(String filmNameEnglish) {
        this.filmNameEnglish = filmNameEnglish;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.ReleaseDate = releaseDate;
    }
}
