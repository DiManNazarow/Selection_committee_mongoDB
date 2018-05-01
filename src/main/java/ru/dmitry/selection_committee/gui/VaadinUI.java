package ru.dmitry.selection_committee.gui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import ru.dmitry.selection_committee.gui.screens.auth.AuthorizationScreen;
import ru.dmitry.selection_committee.server.services.UserServices;

@SpringUI
@Theme("projecttheme")
@PreserveOnRefresh
public class VaadinUI extends UI implements ScreenNavigator.NavigationCallback {

    private ScreenNavigator screenNavigator;

    private Navigator vaadinNavigator;

    private UserServices userServices;

    public VaadinUI(UserServices userServices){
        super();
        this.userServices = userServices;
        vaadinNavigator = new Navigator(this, this);
        screenNavigator = new ScreenNavigator(this, vaadinNavigator);
        setupScreenNavigator();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        vaadinNavigator.navigateTo(screenNavigator.getLastScreen());
    }

    @Override
    protected void refresh(VaadinRequest request){
        vaadinNavigator.navigateTo(screenNavigator.getLastScreen());
    }

    @Override
    public void onScreenOpenListener(String url) {
        vaadinNavigator.navigateTo(url);
    }

    @Override
    public void onBackPressed() {
        Page.getCurrent().getJavaScript().execute("history.back()");
    }

    private void setupScreenNavigator(){
        screenNavigator.setUserServices(userServices);
        AuthorizationScreen authorizationScreen = new AuthorizationScreen(screenNavigator, userServices);
        screenNavigator.addScreen(authorizationScreen.getUrl(), authorizationScreen);
    }
}
