package ru.dmitry.selection_committee.gui.screens.profile.mvp;

import ru.dmitry.selection_committee.gui.mvp.MvpView;

public interface EnrolleProfileScreenView extends MvpView {

    void onFirstNameEmpty();

    void onSecondNameEmpty();

    void onPatronymicEmpty();

    void onDateBirthEmpty();

    void onPasportNameEmpty();

    void onPhoneNumberEmpty();

    void onCityEmpty();

    void onStreetEmpty();

    void onHouseEmpty();

    void onFlatEmpty();

    void onAttestatEmpty();

    void onSchoolEmpty();

    void onSpecialityNotChosen();

    void onProfileSuccessEdit();

    void onProfileEditFailed();

    void onSignOut();

}
