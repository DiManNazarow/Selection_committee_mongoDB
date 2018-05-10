package ru.dmitry.selection_committee.gui.screens.registration;

import com.vaadin.ui.Button;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.admin.AdminMainPageScreen;
import ru.dmitry.selection_committee.gui.screens.profile.State;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.services.UserServices;

public class AdminRegistrationScreen extends RegistrationScreen {

    private final String URL = "admin_registration_screen";

    public AdminRegistrationScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, State.REGISTER);
    }

    @Override
    protected String getLoginHint(){
        return R.Strings.LOGIN_ADMIN;
    }

    @Override
    protected void onButtonRegistrationClick(Button.ClickEvent clickEvent){
        registerScreenPresenter.registerAdmin();
    }

    @Override
    public void onSuccessRegister(Admin admin) {
        screenNavigator.setAuthUser(admin);
        AdminMainPageScreen adminMainPageScreen = new AdminMainPageScreen(screenNavigator);
        screenNavigator.openScreen(adminMainPageScreen.getUrl(), adminMainPageScreen);
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
