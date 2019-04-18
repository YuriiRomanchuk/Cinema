package com.cinema.controller;

import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class ErrorController {

    public View getErrorPage(Exception e) {
        View view = new ViewModel("WEB-INF/jsp/error.jsp");
        view.addParameter("error", e);
        return view;
    }
}
