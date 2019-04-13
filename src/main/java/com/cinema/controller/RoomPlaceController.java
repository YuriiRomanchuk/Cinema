package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.service.RoomPlaceService;
import com.cinema.service.RoomService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

import java.util.List;

public class RoomPlaceController {

    private final RoomPlaceService roomPlaceService;
    private final RoomService roomService;

    public RoomPlaceController(RoomPlaceService roomPlaceService, RoomService roomService) {
        this.roomPlaceService = roomPlaceService;
        this.roomService = roomService;
    }

    public View showRoomPlace() {
        View view = new ViewModel("WEB-INF/jsp/admin/admin-add-room-place.jsp");
        view.addParameter("roomsDto", roomService.receiveAllRoomsDto());
        return view;
    }


    public View createRoomPlace(List<RoomPlaceDto> roomPlacesDto) {
        View view;
        try {
            roomPlaceService.createRoomPlaceService(roomPlacesDto);
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", "Room places added!");
        } catch (ServiceException e) {
            view = new ViewModel("admin-add-room-place-body");
            view.addParameter("roomPlacesDto", roomPlacesDto);
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }
}
