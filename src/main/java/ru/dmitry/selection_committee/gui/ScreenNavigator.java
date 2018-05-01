package ru.dmitry.selection_committee.gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.screens.base.Screen;
import ru.dmitry.selection_committee.server.services.UserServices;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ScreenNavigator {

    private UserServices userServices;

    public interface NavigationCallback {
        void onScreenOpenListener(String url);
        void onBackPressed();
    }

    private TreeMap<String, View> screens;

    private NavigationCallback navigationCallback;

    private Navigator vaadinNavigator;

    public ScreenNavigator(@NotNull NavigationCallback navigationCallback, @NotNull Navigator vaadinNavigator){
        this.navigationCallback = navigationCallback;
        this.vaadinNavigator = vaadinNavigator;
        screens = new TreeMap<>();
    }

    public void openScreen(String url, View screen) {
        throwNullNavigatorExceptionIfNecessary();
        if (!screens.containsKey(url)){
            screens.put(url, screen);
            vaadinNavigator.addView(url, screen);
        }
        navigationCallback.onScreenOpenListener(url);
    }
    public void goBack(){
        throwNullNavigatorExceptionIfNecessary();
        navigationCallback.onBackPressed();
    }

    public UserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    public void addScreen(String url, View screen){
        if (!screens.containsKey(url)){
            screens.put(url, screen);
            vaadinNavigator.addView(url, screen);
        }
    }

    public String getLastScreen(){
        if (!screens.isEmpty()){
            return screens.lastEntry().getKey();
        } else {
            return "/";
        }
    }

    private void throwNullNavigatorExceptionIfNecessary(){
        if (navigationCallback == null) throw new NullPointerException("ScreenNavigator can not be null");
    }

}
