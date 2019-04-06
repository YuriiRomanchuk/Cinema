package com.cinema.controller;

import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class WelcomeController {

    public View showIndexPage() {
        return new ViewModel("WEB-INF/jsp/index.jsp");
    }

    public View showIndexPageRedirect() {
        return new RedirectViewModel(new ViewModel("index"));
    }
}
