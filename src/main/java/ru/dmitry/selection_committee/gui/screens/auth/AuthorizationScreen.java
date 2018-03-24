package ru.dmitry.selection_committee.gui.screens.auth;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import ru.dmitry.selection_committee.resourse.R;

public class AuthorizationScreen extends CustomLayout {

    public AuthorizationScreen() {
        super("authorization_screen");
        setSizeFull();
        getScreenComponents();
    }

    protected void getScreenComponents() {

        Label welcome = new Label(R.Strings.AUTH_WELCOME);
        welcome.addStyleName("v-label-welcome");
        addComponent(welcome, "welcome");
        Label introduce = new Label(R.Strings.AUTH_INTRODUCE);
        introduce.addStyleName("v-label-introduce");
        addComponent(introduce, "introduce");
        Label observe = new Label(R.Strings.AUTH_OBSERVE);
        observe.addStyleName("v-label-observe");
        addComponent(observe, "observe");
        Label control = new Label(R.Strings.AUTH_CONTROL);
        control.addStyleName("v-label-control");
        addComponent(control, "control");

        Button registrationButton = new Button(R.Strings.REGISTRATION_BUTTON_TEXT);
        registrationButton.addStyleName("v-button_registration");
        addComponent(registrationButton, "registration_button");

        Label signIn = new Label(R.Strings.SIGN_IN);
        signIn.addStyleName("v-label-sign_in");
        addComponent(signIn, "sign_in");

        TextField login = new TextField(R.Strings.LOGIN);
        addComponent(login, "login");
        TextField password = new TextField(R.Strings.PASSWORD);
        addComponent(password, "password");

        Button authButton = new Button();
        authButton.setCaptionAsHtml(true);
        authButton.setCaption(R.Strings.AUTH);
        authButton.addStyleName("v-button_auth");
        addComponent(authButton, "auth_button");

    }

}
