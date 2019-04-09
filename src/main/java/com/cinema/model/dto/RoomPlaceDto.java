package com.cinema.model.dto;

public class RoomPlaceDto {

    private int id;
    private int row;
    private int place;
    private RoomDto roomDto;

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }
}
