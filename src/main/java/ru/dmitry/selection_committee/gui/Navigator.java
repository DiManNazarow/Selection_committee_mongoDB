package ru.dmitry.selection_committee.gui;

import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.gui.screens.Screen;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

public class Navigator {

    public interface NavigationCallback {
        void onScreenOpenListener(Component screen);
        void onBackPressed();
    }

    private long deep = 0;

    private HashMap<Long, Component> screens;

    private NavigationCallback navigationCallback;

    public Navigator(@NotNull NavigationCallback navigationCallback){
        this.navigationCallback = navigationCallback;
        screens = new HashMap<>();
    }

    public void openScreen(Component screen){
        throwNullNavigatorExceptionIfNecessary();
        ++deep;
        screens.put(deep, screen);
        navigationCallback.onScreenOpenListener(screen);
    }

    public void goBack(){
        throwNullNavigatorExceptionIfNecessary();
        screens.remove(deep);
        --deep;
        navigationCallback.onBackPressed();
    }

    private void throwNullNavigatorExceptionIfNecessary(){
        if (navigationCallback == null) throw new NullPointerException("Navigator can not be null");
    }

}
