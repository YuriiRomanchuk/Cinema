import com.cinema.config.ComponentInitializer;
import com.cinema.controller.WelcomeController;
import com.cinema.servlet.RequestResolver;

public class MainClass {

    public static void main(String[] args) {
        ComponentInitializer componentInitializer = new ComponentInitializer();
        RequestResolver requestResolver = ComponentInitializer.getInstance().getRequestResolver();
        System.out.println(componentInitializer.getRequestResolver());
    }
}
