package com.cinema.controller;

import com.cinema.service.TicketService;

public class TicketController {

    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    public void buyTicket() {


    }

   /* public View showSessionRoom(){
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-session-room.jsp");
            *//*ticketService.receiveLisOfTickets();*//*

            return view;
        } catch (ServiceException e) {
            view = new ViewModel("admin-personal-area");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            return new RedirectViewModel(view);
        }
    }*/


}
