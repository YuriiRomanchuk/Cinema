package com.cinema.controller;

import com.cinema.model.dto.RoomDto;
import com.cinema.service.RoomService;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public View createRoom(RoomDto roomDto) {

        return new ViewModel();
    }

}
