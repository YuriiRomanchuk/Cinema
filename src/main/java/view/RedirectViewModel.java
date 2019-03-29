package view;

import java.util.Map;

public class RedirectViewModel implements View {

    private View view;

    public RedirectViewModel(View view) {
        this.view = view;
    }

    @Override
    public String getPageUrl() {
        return view.getPageUrl();
    }

    @Override
    public void addParameter(String key, Object value) {
        view.addParameter(key, value);
    }

    @Override
    public void removeParameter(String key) {
        view.removeParameter(key);
    }

    @Override
    public Map<String, Object> getParameters() {
        return view.getParameters();
    }

    @Override
    public View getView() {
        return view;
    }
}
