package ru.dmitry.selection_committee.gui.screens.auth;

import ru.dmitry.selection_committee.gui.mvp.MvpView;
import ru.dmitry.selection_committee.server.models.User;

public interface AuthScreenView extends MvpView {

    void onAuthAdmin();

    void onAuthEnrolle();

    void onLoginEmpty();

    void onPasswordEmpty();

    void onAuthError();

}
