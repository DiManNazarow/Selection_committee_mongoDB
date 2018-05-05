package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

public interface AddInstitutionScreenView extends MvpView {

    void onSuccessAdded();

    void onFailAdded();

    void onShortNameEmpty();

    void onFullNameEmpty();

    void onCityEmpty();

    void onStreetEmpty();

    void onHouseEmpty();

}