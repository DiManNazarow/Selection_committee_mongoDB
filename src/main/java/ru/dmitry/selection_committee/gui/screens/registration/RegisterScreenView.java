package ru.dmitry.selection_committee.gui.screens.registration;

import ru.dmitry.selection_committee.gui.mvp.MvpView;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;

public interface RegisterScreenView extends MvpView {

    void onSuccessRegister(Enrollee enrollee);

    void onSuccessRegister(Admin admin);

    void onFailRegister();

    void onLoginEmpty();

    void onEmailEmpty();

    void onPasswordEmpty();

    void onRetypePassEmpty();

    void onRetypePassWrong();

}
