package ru.dmitry.selection_committee.gui.screens.registration;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.screens.base.Screen;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.gui.views.LoginInputView;
import ru.dmitry.selection_committee.gui.views.PasswordInputView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.repositories.UserRepository;
import ru.dmitry.selection_committee.server.services.UserServices;

public class RegistrationScreen extends CustomLayoutScreen implements RegisterScreenView {

    private final String URL = "registration";

    private InputView loginInputView;

    private InputView emailInputView;

    private InputView passwordInputView;

    private InputView retypePasswordInputView;

    private Button registrationButton;

    private RegisterScreenPresenter registerScreenPresenter;

    private UserServices userServices;

    @Autowired
    public RegistrationScreen(ScreenNavigator screenNavigator, UserServices userServices) {
        super(screenNavigator, "registration_screen");
        this.userServices = userServices;
        setSizeFull();
        addStyleName("v-root");
        registerScreenPresenter = new RegisterScreenPresenter(this, userServices);
    }

    protected void addComponents() {

        Label header = new Label(R.Strings.REGISTRATION);
        header.addStyleName("v-headerlabel");
        addComponent(header, "header");

        Image imageLogin = new Image(null, new ThemeResource("img/ic_perm_identity_black_36px.svg"));
        imageLogin.setWidth(30, Unit.PIXELS);
        imageLogin.setHeight(30, Unit.PIXELS);
        addComponent(imageLogin, "login_icon");

        loginInputView = new InputView(new TextField(), R.Strings.LOGIN);
        loginInputView.setTextChangeListener(this::onLoginTextChanged);
        loginInputView.addInputStyle("v-textfield-register");
        addComponent(loginInputView, "login_field");

        Image imageEmail = new Image(null, new ThemeResource("img/ic_local_post_office_black_36px.svg"));
        imageEmail.setWidth(25, Unit.PIXELS);
        imageEmail.setHeight(25, Unit.PIXELS);
        addComponent(imageEmail, "email_icon");

        emailInputView = new InputView(new TextField(), R.Strings.EMAIL);
        emailInputView.setTextChangeListener(this::onEmailTextChanged);
        emailInputView.addInputStyle("v-textfield-register");
        addComponent(emailInputView, "email_field");

        Image imagePass = new Image(null, new ThemeResource("img/ic_lock_outline_black_36px.svg"));
        imagePass.setWidth(25, Unit.PIXELS);
        imagePass.setHeight(25, Unit.PIXELS);
        addComponent(imagePass, "password_icon");

        passwordInputView = new PasswordInputView(R.Strings.PASSWORD);
        passwordInputView.setTextChangeListener(this::onPasswordTextChanged);
        passwordInputView.addInputStyle("v-textfield-register");
        addComponent(passwordInputView, "password_field");

        Image imagePassRetype = new Image(null, new ThemeResource("img/ic_lock_outline_black_36px.svg"));
        imagePassRetype.setWidth(25, Unit.PIXELS);
        imagePassRetype.setHeight(25, Unit.PIXELS);
        addComponent(imagePassRetype, "retype_icon");

        retypePasswordInputView = new PasswordInputView(R.Strings.RETYPE_PASSWORD);
        retypePasswordInputView.setTextChangeListener(this::onRetypePasswordChanged);
        retypePasswordInputView.addInputStyle("v-textfield-register");
        addComponent(retypePasswordInputView, "retype_field");

        registrationButton = new Button(R.Strings.REGISTRATION_BUTTON_TEXT);
        registrationButton.addStyleName("v-button_registration");
        registrationButton.addClickListener(this::onButtonRegistrationClick);
        addComponent(registrationButton, "register_button");

    }

    @Override
    public String getUrl() {
        return URL;
    }

    private void onButtonRegistrationClick(Button.ClickEvent clickEvent){
        registerScreenPresenter.register();
    }

    private void onLoginTextChanged(CharSequence text){
        loginInputView.hideError();
        registerScreenPresenter.setLogin(text.toString());
    }

    private void onEmailTextChanged(CharSequence text){
        emailInputView.hideError();
        registerScreenPresenter.setEmail(text.toString());
    }

    private void onPasswordTextChanged(CharSequence text){
        passwordInputView.hideError();
        registerScreenPresenter.setPassword(text.toString());
    }

    private void onRetypePasswordChanged(CharSequence text){
        retypePasswordInputView.hideError();
        registerScreenPresenter.setRetypePassword(text.toString());
    }

    @Override
    public void onSuccessRegister() {
        Notification.show("Success");
        screenNavigator.goBack();
    }

    @Override
    public void onFailRegister() {
        Notification.show("Fail");
    }

    @Override
    public void onLoginEmpty() {
        loginInputView.showEmptyTextError();
    }

    @Override
    public void onEmailEmpty() {
        emailInputView.showEmptyTextError();
    }

    @Override
    public void onPasswordEmpty() {
        passwordInputView.showEmptyTextError();
    }

    @Override
    public void onRetypePassEmpty() {
        retypePasswordInputView.showEmptyTextError();
    }

    @Override
    public void onRetypePassWrong() {
        retypePasswordInputView.showError(R.Strings.Errors.RETYPE_PASS_WRONG);
    }
}
