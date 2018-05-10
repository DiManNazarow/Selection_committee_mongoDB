package ru.dmitry.selection_committee.gui.screens.auth;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.admin.AdminMainPageScreen;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.screens.profile.EditEnrolleProfileScreen;
import ru.dmitry.selection_committee.gui.screens.profile.EnrolleProfileScreen;
import ru.dmitry.selection_committee.gui.screens.profile.State;
import ru.dmitry.selection_committee.gui.screens.registration.AdminRegistrationScreen;
import ru.dmitry.selection_committee.gui.screens.registration.RegistrationScreen;
import ru.dmitry.selection_committee.gui.views.LoginInputView;
import ru.dmitry.selection_committee.gui.views.PasswordInputView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.services.UserServices;

public class AuthorizationScreen extends CustomLayoutScreen implements AuthScreenView {

    public static final String URL = "/";

    private AuthScreenPresenter authScreenPresenter;

    private String login;

    private String password;

    private LoginInputView loginField;

    private PasswordInputView passwordFiled;

    @Autowired
    public AuthorizationScreen(ScreenNavigator screenNavigator, UserServices userServices) {
        super(screenNavigator, "authorization_screen");
        authScreenPresenter = new AuthScreenPresenter(screenNavigator.getUserServices(),this);
        setSizeFull();
    }

    @Override
    protected void setupComponents(Object object) {

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
        registrationButton.addClickListener(this::onRegisterButtonClick);
        addComponent(registrationButton, "registration_button");

        Label signIn = new Label(R.Strings.SIGN_IN);
        signIn.addStyleName("v-label-sign_in");
        addComponent(signIn, "sign_in");

        loginField = new LoginInputView(R.Strings.LOGIN);
        loginField.addInputStyle("v-textfield-login");
        loginField.setWidth("80%");

        loginField.setTextChangeListener(text -> {
            login = text.toString();
            loginField.hideError();
        });

        addComponent(loginField, "login");

        passwordFiled = new PasswordInputView(R.Strings.PASSWORD);
        passwordFiled.addInputStyle("v-textfield-login");
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

        authButton.addClickListener(this::onAuthButtonClick);

        addComponent(authButton, "auth_button");

    }

    private void onAuthButtonClick(Button.ClickEvent clickEvent){
        authScreenPresenter.auth(login, password);
    }

    private void onRegisterButtonClick(Button.ClickEvent clickEvent){
        RegistrationScreen registrationScreen = new RegistrationScreen(screenNavigator, State.REGISTER);
        screenNavigator.openScreen(registrationScreen.getUrl(), registrationScreen);
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public void onAuthAdmin(Admin admin) {
        screenNavigator.setAuthUser(admin);
        AdminMainPageScreen adminMainPageScreen = new AdminMainPageScreen(screenNavigator);
        screenNavigator.openScreen(adminMainPageScreen.getUrl(), adminMainPageScreen);
    }

    @Override
    public void onAuthEnrolle(Enrollee enrollee) {
        screenNavigator.setAuthUser(enrollee);
        EditEnrolleProfileScreen enrolleProfileScreen = new EditEnrolleProfileScreen(screenNavigator, enrollee);
        screenNavigator.openScreen(enrolleProfileScreen.getUrl(), enrolleProfileScreen);
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
