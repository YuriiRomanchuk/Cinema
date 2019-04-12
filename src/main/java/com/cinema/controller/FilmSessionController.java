package com.cinema.controller;

import com.cinema.exception.ServiceException;
import com.cinema.model.dto.FilmSessionDto;
import com.cinema.service.FilmService;
import com.cinema.service.FilmSessionService;
import com.cinema.service.RoomService;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class FilmSessionController {

    private final FilmSessionService filmSessionService;
    private final FilmService filmService;
    private final RoomService roomService;

    public FilmSessionController(FilmSessionService filmSessionService, FilmService filmService, RoomService roomService) {
        this.filmSessionService = filmSessionService;
        this.filmService = filmService;
        this.roomService = roomService;
    }

    public View showFilmSessionPage() {
        return new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
    }


/*
    public View addSession(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
            filmSessionService.addFilmSession(filmSessionDto);


          */
/*
            view.addParameter("filmsSessionDto", filmSessionService.addFilmSession(filmSessionDto));
            view.addParameter("filmsDto", filmService.receiveAllFilmsDto());
            view.addParameter("roomsDto", roomService.recieveAllRoomsDto());
            view.addParameter("currentDate", filmSessionDto.getDate());
            view.addParameter("currentFilm_id", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
        *//*
    return view;
        } catch (ServiceException e) {
         */
/*   view = new ViewModel("admin-session");
         *//*
*/
/*   view.addParameter("currentDate", filmSessionDto.getDate());
            view.addParameter("currentFilm_id", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
        *//*
*/
/*    view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
          *//*
  return new RedirectViewModel(view);
        }



    }
*/


    public View showFilmSessionPageFilters(FilmSessionDto filmSessionDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-session.jsp");
            view.addParameter("filmsSessionDto", filmSessionService.showFilmSession(filmSessionDto));
            view.addParameter("filmsDto", filmService.receiveAllFilmsDto());
            view.addParameter("roomsDto", roomService.recieveAllRoomsDto());
            view.addParameter("currentDate", filmSessionDto.getDate());
            view.addParameter("currentFilm_id", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-session");
         /*   view.addParameter("currentDate", filmSessionDto.getDate());
            view.addParameter("currentFilm_id", filmSessionDto.getFilmDto() != null ? filmSessionDto.getFilmDto().getId() : -1);
        */    view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }
}
