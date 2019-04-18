package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.RoomDto;
import com.cinema.service.RoomService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public View createRoom(RoomDto roomDto) {

        View view;
        try {
            roomService.createRoom(roomDto);
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", "Film added!");
        } catch (ServiceException e) {
            view = new ViewModel("admin-add-room");
            view.addParameter("filmDto", roomDto);
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

    public View showAddRoomPage() {
        return new ViewModel("WEB-INF/jsp/admin/admin-add-room.jsp");
    }

}
