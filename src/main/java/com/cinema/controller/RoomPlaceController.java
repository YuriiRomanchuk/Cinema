package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.RoomPlaceDto;
import com.cinema.service.RoomPlaceService;
import com.cinema.service.RoomService;
import com.cinema.validator.AddRoomPlaceValidator;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

import java.util.List;

public class RoomPlaceController {

    private final RoomPlaceService roomPlaceService;
    private final RoomService roomService;
    private final AddRoomPlaceValidator addRoomPlaceValidator;

    public RoomPlaceController(RoomPlaceService roomPlaceService,
                               RoomService roomService,
                               AddRoomPlaceValidator addRoomPlaceValidator) {
        this.roomPlaceService = roomPlaceService;
        this.roomService = roomService;
        this.addRoomPlaceValidator = addRoomPlaceValidator;
    }

    public View showRoomPlace() {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-add-room-place.jsp");
            view.addParameter("roomsDto", roomService.receiveAllRoomsDto());
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("admin-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }

    public View createRoomPlace(List<RoomPlaceDto> roomPlacesDto) {
        View view;
        try {
            roomPlaceService.createRoomPlaceService(roomPlacesDto);
            view = receiveViewModel("admin-personal-area", "Room places added!");
        } catch (ServiceException e) {
            view = receiveViewModel("admin-add-room-place", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            view.addParameter("roomPlacesDto", roomPlacesDto);
        }
        return new RedirectViewModel(view);
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        return view;
    }
}
