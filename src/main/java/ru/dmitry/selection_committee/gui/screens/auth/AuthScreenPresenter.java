package ru.dmitry.selection_committee.gui.screens.auth;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Role;
import ru.dmitry.selection_committee.server.models.User;
import ru.dmitry.selection_committee.server.services.UserServices;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class AuthScreenPresenter extends BasePresenter<AuthScreenView> {

    private UserServices userServices;

    @Autowired
    public AuthScreenPresenter(UserServices userServices, AuthScreenView mvpView) {
        super(mvpView);
        this.userServices = userServices;
    }

    public void auth(String login, String password){
        getViewState().onAuthAdmin();
//        if (AppTextUtils.isTextEmpty(login)){
//            getViewState().onLoginEmpty();
//            return;
//        }
//        if (AppTextUtils.isTextEmpty(password)){
//            getViewState().onPasswordEmpty();
//            return;
//        }
//        User user = userServices.findUser(login, String.valueOf(password.hashCode()));
//        if (user != null){
//            if (user.getRole() == Role.ADMIN.getRoleCode()){
//                getViewState().onAuthAdmin();
//            } else {
//                getViewState().onAuthEnrolle();
//            }
//        } else {
//            getViewState().onAuthError();
//        }
    }

}
