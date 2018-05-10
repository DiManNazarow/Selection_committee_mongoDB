package ru.dmitry.selection_committee.gui.screens.auth;

import ru.dmitry.selection_committee.gui.mvp.MvpView;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.User;

public interface AuthScreenView extends MvpView {

    void onAuthAdmin(Admin admin);

    void onAuthEnrolle(Enrollee enrollee);

    void onLoginEmpty();

    void onPasswordEmpty();

    void onAuthError();

}
