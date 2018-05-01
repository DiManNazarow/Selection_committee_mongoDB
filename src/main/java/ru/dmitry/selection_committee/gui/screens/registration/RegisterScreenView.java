package ru.dmitry.selection_committee.gui.screens.registration;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

public interface RegisterScreenView extends MvpView {

    void onSuccessRegister();

    void onFailRegister();

    void onLoginEmpty();

    void onEmailEmpty();

    void onPasswordEmpty();

    void onRetypePassEmpty();

    void onRetypePassWrong();

}
