package com.cinema.controller;

import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class WelcomeController {

    public View showIndexPage() {
        View view = new ViewModel("WEB-INF/index.jsp");
        return view;
    }
}
