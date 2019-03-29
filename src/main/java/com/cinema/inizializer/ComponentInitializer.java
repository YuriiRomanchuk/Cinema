package com.cinema.inizializer;

import com.cinema.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ComponentInitializer {

    private static ComponentInitializer initializer;
    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();

    private ComponentInitializer() {

    }

    public static ComponentInitializer getInitializer() {

        if (initializer == null) {
            initializer = new ComponentInitializer();
        }

        return initializer;
    }

    private void initializeGetControllers() {


    }

    private void initializePostControllers() {


    }

    public Map<String, Function<HttpServletRequest, View>> getGetControllers() {
        return getControllers;
    }

    public Map<String, Function<HttpServletRequest, View>> getPostControllers() {
        return postControllers;
    }
}
