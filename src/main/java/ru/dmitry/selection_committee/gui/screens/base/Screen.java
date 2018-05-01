package ru.dmitry.selection_committee.gui.screens.base;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.gui.ScreenNavigator;

public abstract class Screen extends VerticalLayout implements View {

    private ScreenNavigator screenNavigator;

    public Screen(ScreenNavigator screenNavigator){
        this.screenNavigator = screenNavigator;
        addComponents();
    }

    protected abstract void addComponents();

    public abstract String getUrl();

}
