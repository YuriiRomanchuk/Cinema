package com.cinema.servlet;

import com.cinema.config.WebComponentInitializer;
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


    public RequestResolver(WebComponentInitializer webComponentInitializer) {

        getControllers.put("/index", r -> webComponentInitializer.getWelcomeController().showIndexPage(webComponentInitializer.getFilmSessionDtoConverter().convert(r)));
        getControllers.put("/registration-form", r -> webComponentInitializer.getUserController().showRegistrationPage());
        getControllers.put("/login", r -> webComponentInitializer.getUserController().showUserLoginPage());
        getControllers.put("/admin-personal-area", r -> webComponentInitializer.getUserController().showAdminPersonalArea());
        getControllers.put("/user-personal-area", r -> webComponentInitializer.getUserController().showUserPersonalArea(webComponentInitializer.getUserDtoConverter().convertFromRequestForUserId(r)));
        getControllers.put("/logout", r -> webComponentInitializer.getUserController().logout());
        getControllers.put("/admin-add-film", r -> webComponentInitializer.getFilmController().showAddFilmPage());
        getControllers.put("/admin-show-all-films", r -> webComponentInitializer.getFilmController().showAllFilms());
        getControllers.put("/admin-add-room", r -> webComponentInitializer.getRoomController().showAddRoomPage());
        getControllers.put("/admin-add-room-place", r -> webComponentInitializer.getRoomPlaceController().showRoomPlace());
        getControllers.put("/admin-session", r -> webComponentInitializer.getFilmSessionController().showFilmSessionPageFiltersAdmin(webComponentInitializer.getFilmSessionDtoConverter().convert(r)));
        getControllers.put("/admin-session-room", r -> webComponentInitializer.getTicketController().showSessionRoom());
        getControllers.put("/user-session", r -> webComponentInitializer.getFilmSessionController().showFilmSessionPageFiltersUser(webComponentInitializer.getFilmSessionDtoConverter().convert(r)));
        getControllers.put("/user-session-room", r -> webComponentInitializer.getTicketController().showUserSessionRoom());

        postControllers.put("/login", r -> webComponentInitializer.getUserController().loginUser(webComponentInitializer.getUserLoginDtoConverter().convert(r)));
        postControllers.put("/registration-form", r -> webComponentInitializer.getUserController().createUser(webComponentInitializer.getUserConverter().convert(r)));
        postControllers.put("/change_language", r -> webComponentInitializer.getChangeLanguageController().changeLanguage());
        postControllers.put("/admin-add-film", r -> webComponentInitializer.getFilmController().createFilm(webComponentInitializer.getFilmDtoConverter().convert(r)));
        postControllers.put("/admin-add-room", r -> webComponentInitializer.getRoomController().createRoom(webComponentInitializer.getRoomDtoConverter().convert(r)));
        postControllers.put("/admin-add-room-place", r -> webComponentInitializer.getRoomPlaceController().createRoomPlace(webComponentInitializer.getRoomPlaceDtoConverter().convert(r)));
        postControllers.put("/admin-session", r -> webComponentInitializer.getFilmSessionController().showFilmSessionPageFiltersAdmin(webComponentInitializer.getFilmSessionDtoConverter().convert(r)));
        postControllers.put("/add-session", r -> webComponentInitializer.getFilmSessionController().addFilmSession(webComponentInitializer.getFilmSessionDtoConverter().convertFilmSessionWithLineAdd(r)));
        postControllers.put("/delete-session", r -> webComponentInitializer.getFilmSessionController().deleteFilmSession(webComponentInitializer.getFilmSessionDtoConverter().convertFilmSessionWithLineDelete(r)));
        postControllers.put("/show-session", r -> webComponentInitializer.getFilmSessionController().showSessionRoomAdmin(webComponentInitializer.getFilmSessionDtoConverter().convertFilmSessionWithLineShow(r)));
        postControllers.put("/user-session", r -> webComponentInitializer.getFilmSessionController().showFilmSessionPageFiltersUser(webComponentInitializer.getFilmSessionDtoConverter().convert(r)));
        postControllers.put("/show-user-session", r -> webComponentInitializer.getFilmSessionController().showSessionRoomUser(webComponentInitializer.getFilmSessionDtoConverter().convertFilmSessionWithLineBuy(r)));
        postControllers.put("/buy-place", r -> webComponentInitializer.getTicketController().buyTicket(webComponentInitializer.getTicketDtoConverter().convert(r)));
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
