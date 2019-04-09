package com.cinema.servlet;

import com.cinema.config.ComponentInitializer;
import com.cinema.controller.*;
import com.cinema.view.RedirectViewModel;
import com.cinema.view.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class RequestResolver {

    private static final String VIEW_ATTRIBUTE = "VIEW_ATTRIBUTE";

    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();


    public RequestResolver(WelcomeController welcomeController,
                           UserController userController,
                           ChangeLanguageController changeLanguageController,
                           FilmController filmController,
                           RoomController roomController,
                           RoomPlaceController roomPlaceController) {

        getControllers.put("/index", r -> welcomeController.showIndexPage());
        getControllers.put("/registration-form", r -> userController.showRegistrationPage());
        getControllers.put("/login", r -> userController.showUserLoginPage());
        getControllers.put("/admin-personal-area", r -> userController.showAdminPersonalArea());
        getControllers.put("/user-personal-area", r -> userController.showUserPersonalArea());
        getControllers.put("/logout", r -> userController.logout());
        getControllers.put("/admin-add-film", r -> filmController.showAddFilmPage());
        getControllers.put("/admin-add-room", r -> roomController.showAddRoomPage());
        getControllers.put("/admin-add-room-place", r -> roomPlaceController.showRoomPlace());


        postControllers.put("/login", r -> userController.loginUser(ComponentInitializer.getInstance().getUserLoginDtoConverter().convertFromHttpRequest(r)));
        postControllers.put("/registration-form", r -> userController.createUser(ComponentInitializer.getInstance().getUserConverter().convertFromHttpRequest(r)));
        postControllers.put("/change_language", r -> changeLanguageController.changeLanguage());
        postControllers.put("/admin-add-film", r -> filmController.createFilm(ComponentInitializer.getInstance().getFilmDtoConverter().convertFromHttpRequest(r)));
        postControllers.put("/admin-add-room", r -> roomController.createRoom(ComponentInitializer.getInstance().getRoomDtoConverter().convertFromHttpRequest(r)));
        postControllers.put("/admin-add-room-place", r -> roomPlaceController.createRoomPlace(ComponentInitializer.getInstance().getRoomPlaceDtoConverter().convertFromHttpRequest(r)));

    }

    public void resolveGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reference(getView(request, getControllers), request, response);
    }

    public void resolvePostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reference(getView(request, postControllers), request, response);
    }

    private void reference(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (view instanceof RedirectViewModel) {
            request.getSession().setAttribute(VIEW_ATTRIBUTE, view.getView());
            response.sendRedirect(view.getPageUrl());
        } else if (view != null) {
            view.getParameters().forEach(request::setAttribute);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + view.getPageUrl());
            requestDispatcher.forward(request, response);
        }
    }

    private View getView(HttpServletRequest request, Map<String, Function<HttpServletRequest, View>> sourceController) {
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/main", "");

        View originView = (View) request.getSession().getAttribute(VIEW_ATTRIBUTE);
        request.getSession().removeAttribute(VIEW_ATTRIBUTE);

        View destinationView = Optional.ofNullable(sourceController.get(requestURI)).map(f -> f.apply(request)).orElse(null);
        if (originView != null && destinationView != null) {
            originView.getParameters().forEach(destinationView::addParameter);
        }
        return destinationView;
    }

}
