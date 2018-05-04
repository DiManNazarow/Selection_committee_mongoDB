package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

public interface AddInstitutionScreenView extends MvpView {

    void onSuccessAdded();

    void onFailAdded();

}
