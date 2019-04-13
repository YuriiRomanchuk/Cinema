package com.cinema.controller;

import com.cinema.service.TicketService;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    public void buyTicket() {


    }

    public View showSessionRoom(){
       return new ViewModel("WEB-INF/jsp/admin/admin-session-room.jsp");
    }

}
