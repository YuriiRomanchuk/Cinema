package com.cinema.model.entity;

public class RoomPlace {

    private int id;
    private int row;
    private int place;
    private Room room;


    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    public Room getRoom() {
        return room;
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

    public void setRoom(Room room) {
        this.room = room;
    }
}
