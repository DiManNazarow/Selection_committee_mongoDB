package ru.dmitry.selection_committee.gui.screens.registration;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.services.UserServices;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class RegisterScreenPresenter extends BasePresenter<RegisterScreenView> {

    private String login;

    private String email;

    private String password;

    private String retypePassword;

    private UserServices userServices;

    public RegisterScreenPresenter(RegisterScreenView mvpView, UserServices userServices) {
        super(mvpView);
        this.userServices = userServices;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public void register(){
        if (check(false)) {
            Enrollee enrollee = new Enrollee();
            if (!AppTextUtils.isTextEmpty(login)) {
                enrollee.setLogin(login);
            }
            enrollee.setEmail(email);
            enrollee.setPassword(password);
            String id = userServices.insertEnrolle(enrollee);
            if (!AppTextUtils.isTextEmpty(id)) {
                enrollee.setId(id);
                getViewState().onSuccessRegister(enrollee);
            } else {
                getViewState().onFailRegister();
            }
        }
    }

    public void registerAdmin(){
        if (check(true)){
            Admin admin = new Admin();
            if (!AppTextUtils.isTextEmpty(login)){
                admin.setLogin(login);
            }
            admin.setEmail(email);
            admin.setPassword(password);
            String id = userServices.insertAdmin(admin);
            if (!AppTextUtils.isTextEmpty(id)) {
                admin.setId(id);
                getViewState().onSuccessRegister(admin);
            } else {
                getViewState().onFailRegister();
            }
        }
    }

    private boolean check(boolean admin){
        if (admin){
            if (AppTextUtils.isTextEmpty(login)){
                getViewState().onLoginEmpty();
                return false;
            }
        }
        if (AppTextUtils.isTextEmpty(email)){
            getViewState().onEmailEmpty();
            return false;
        }
        if (AppTextUtils.isTextEmpty(password)){
            getViewState().onPasswordEmpty();
            return false;
        }
        if (AppTextUtils.isTextEmpty(retypePassword)){
            getViewState().onRetypePassEmpty();
            return false;
        }
        if (!password.equals(retypePassword)){
            getViewState().onRetypePassWrong();
            return false;
        }
        return true;
    }

}
