package ru.dmitry.selection_committee.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import ru.dmitry.selection_committee.gui.screens.auth.AuthorizationScreen;

@SpringUI
@Theme("projecttheme")
public class VaadinUI extends UI implements Navigator.NavigationCallback {

    private Navigator navigator;

    public VaadinUI(){
        super();
        navigator = new Navigator(this);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        AuthorizationScreen authorizationScreen = new AuthorizationScreen();
        navigator.openScreen(authorizationScreen);
    }

    @Override
    public void onScreenOpenListener(Component screen) {
        setContent(screen);
    }

    @Override
    public void onBackPressed() {
        Page.getCurrent().getJavaScript().execute("history.back()");
    }
}
