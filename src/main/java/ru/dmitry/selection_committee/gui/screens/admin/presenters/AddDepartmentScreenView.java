package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

public interface AddDepartmentScreenView extends MvpView {

    void onSuccessAdded();

    void onFailAdded();

    void onShortNameEmpty();

    void onFullNameEmpty();

    void onInstitutionNotSelected();

}
