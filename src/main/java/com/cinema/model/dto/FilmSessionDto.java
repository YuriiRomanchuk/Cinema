package com.cinema.model.dto;

public class FilmSessionDto {

    private int id;
    private FilmDto filmDto;
    private RoomDto roomDto;
    private String date;

    public int getId() {
        return id;
    }

    public FilmDto getFilmDto() {
        return filmDto;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFilmDto(FilmDto filmDto) {
        this.filmDto = filmDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
