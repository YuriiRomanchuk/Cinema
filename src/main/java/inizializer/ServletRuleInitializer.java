package inizializer;

import view.View;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ServletRuleInitializer {

    private static ServletRuleInitializer initializer;
    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();


    private ServletRuleInitializer() {

    }

    public static ServletRuleInitializer getInitializer() {

        if (initializer == null) {
            initializer = new ServletRuleInitializer();
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
