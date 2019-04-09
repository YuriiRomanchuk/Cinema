package com.cinema.controller;

import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.service.RoomPlaceService;
import com.cinema.service.RoomService;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class RoomPlaceController {

    private RoomPlaceService roomPlaceService;
    private RoomService roomService;

    public RoomPlaceController(RoomPlaceService roomPlaceService, RoomService roomService) {
        this.roomPlaceService = roomPlaceService;
        this.roomService = roomService;
    }

    public View showRoomPlace() {
        View view = new ViewModel("WEB-INF/jsp/admin/admin-add-room-place.jsp");
        view.addParameter("roomsDto", roomService.recieveAllRoomsDto());
        return view;
    }


    public View createRoomPlace(RoomPlaceDto roomPlaceDto) {

        return new ViewModel("");
    }

}
