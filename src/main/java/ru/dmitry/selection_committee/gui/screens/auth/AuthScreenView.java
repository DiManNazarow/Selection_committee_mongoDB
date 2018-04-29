package ru.dmitry.selection_committee.gui.screens.auth;

import ru.dmitry.selection_committee.gui.mvp.MvpView;
import ru.dmitry.selection_committee.server.models.User;

public interface AuthScreenView extends MvpView {

    void onAuthSuccess(User user);

    void onLoginEmpty();

    void onPasswordEmpty();

    void onAuthError();

}
