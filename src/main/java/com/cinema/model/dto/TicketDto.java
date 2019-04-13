package com.cinema.model.dto;

public class TicketDto {

    private int id;
    private UserDto userDto;
    private RoomPlaceDto roomPlaceDto;
    private FilmSessionDto filmSessionDto;

    public int getId() {
        return id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public RoomPlaceDto getRoomPlaceDto() {
        return roomPlaceDto;
    }

    public FilmSessionDto getFilmSessionDto() {
        return filmSessionDto;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public void setRoomPlaceDto(RoomPlaceDto roomPlaceDto) {
        this.roomPlaceDto = roomPlaceDto;
    }

    public void setFilmSessionDto(FilmSessionDto filmSessionDto) {
        this.filmSessionDto = filmSessionDto;
    }
}
