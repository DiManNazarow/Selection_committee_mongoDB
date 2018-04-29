package ru.dmitry.selection_committee.gui.screens.auth;

import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.gui.views.LoginInputView;
import ru.dmitry.selection_committee.gui.views.PasswordInputView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.User;
import ru.dmitry.selection_committee.server.services.UserServices;

public class AuthorizationScreen extends CustomLayout implements AuthScreenView {

    private UserServices userServices;

    private AuthScreenPresenter authScreenPresenter;

    private String login;

    private String password;

    private LoginInputView loginField;

    private PasswordInputView passwordFiled;

    @Autowired
    public AuthorizationScreen(UserServices userServices) {
        super("authorization_screen");
        this.userServices = userServices;
        authScreenPresenter = new AuthScreenPresenter(userServices,this);
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

        loginField = new LoginInputView(R.Strings.LOGIN);
        loginField.setWidth("80%");

        loginField.setTextChangeListener(text -> {
            login = text.toString();
            loginField.hideError();
        });

        addComponent(loginField, "login");

        passwordFiled = new PasswordInputView(R.Strings.PASSWORD);
        passwordFiled.setWidth("80%");

        passwordFiled.setTextChangeListener(text -> {
            password = text.toString();
            passwordFiled.hideError();
        });

        addComponent(passwordFiled, "password");

        Button authButton = new Button();
        authButton.setCaptionAsHtml(true);
        authButton.setCaption(R.Strings.AUTH);
        authButton.addStyleName("v-button_auth");

        authButton.addClickListener((Button.ClickListener) clickEvent -> {
            authScreenPresenter.auth(login, password);
        });

        addComponent(authButton, "auth_button");

    }


    @Override
    public void onAuthSuccess(User user) {
        loginField.hideError();
        passwordFiled.hideError();
        Notification.show(user.getFirstName());
    }

    @Override
    public void onLoginEmpty() {
        loginField.showEmptyTextError();
    }

    @Override
    public void onPasswordEmpty() {
        passwordFiled.showEmptyTextError();
    }

    @Override
    public void onAuthError() {
        loginField.showWrongAuthDataError();
        passwordFiled.showWrongAuthDataError();
    }
}
