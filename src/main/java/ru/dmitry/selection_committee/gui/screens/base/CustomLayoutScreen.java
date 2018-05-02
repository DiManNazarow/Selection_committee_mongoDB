package ru.dmitry.selection_committee.gui.screens.base;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomLayout;
import ru.dmitry.selection_committee.gui.ScreenNavigator;

public abstract class CustomLayoutScreen extends CustomLayout implements View {

    protected ScreenNavigator screenNavigator;

    public CustomLayoutScreen(ScreenNavigator screenNavigator, String templateName){
        super(templateName);
        this.screenNavigator = screenNavigator;
        addComponents();
    }

    protected abstract void addComponents();

    public abstract String getUrl();

}
