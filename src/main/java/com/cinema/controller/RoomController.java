package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.RoomDto;
import com.cinema.service.RoomService;
import com.cinema.validator.AddRoomValidator;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class RoomController {

    private final RoomService roomService;
    private final AddRoomValidator addRoomValidator;

    public RoomController(RoomService roomService, AddRoomValidator addRoomValidator) {
        this.roomService = roomService;
        this.addRoomValidator = addRoomValidator;
    }

    public View createRoom(RoomDto roomDto) {

        View view;
        try {
            view = validateAddRoom(roomDto);
        } catch (ServiceException e) {
            view = receiveViewModel("admin-add-room", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            view.addParameter("roomDto", roomDto);
        }
        return new RedirectViewModel(view);
    }

    private View validateAddRoom(RoomDto roomDto) throws ServiceException {
        View view;
        String invalidateFields = addRoomValidator.validate(roomDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("admin-add-room", invalidateFields);
            view.addParameter("roomDto", roomDto);
        } else {
            roomService.createRoom(roomDto);
            view = receiveViewModel("admin-personal-area", "Room added!");
        }
        return view;
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        return view;
    }

    public View showAddRoomPage() {
        return new ViewModel("WEB-INF/jsp/admin/admin-add-room.jsp");
    }

}
