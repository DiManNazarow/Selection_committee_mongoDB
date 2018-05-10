package ru.dmitry.selection_committee.gui.screens.base;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import ru.dmitry.selection_committee.gui.ScreenNavigator;

public abstract class CustomLayoutScreen<T> extends CustomLayout implements View {

    protected ScreenNavigator screenNavigator;

    public CustomLayoutScreen(ScreenNavigator screenNavigator, String templateName){
        super(templateName);
        this.screenNavigator = screenNavigator;
        setupComponents(null);
    }

    public CustomLayoutScreen(ScreenNavigator screenNavigator, T data, String templateName){
        super(templateName);
        this.screenNavigator = screenNavigator;
        setupComponents(data);
    }

    protected abstract void setupComponents(T data);

    public abstract String getUrl();

}
