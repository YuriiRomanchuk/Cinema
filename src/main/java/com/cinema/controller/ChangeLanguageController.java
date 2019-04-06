package com.cinema.controller;

import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;
import com.cinema.view.ViewModel;

public class ChangeLanguageController {

    public View changeLanguage() {
        return new RedirectViewModel(new ViewModel("index"));
    }

}
