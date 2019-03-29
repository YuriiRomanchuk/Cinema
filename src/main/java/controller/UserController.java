package controller;

import service.UserService;
import view.View;
import view.ViewModel;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/login.jsp");
    }

}
