package ru.dmitry.selection_committee.gui.screens.profile;

import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.models.Status;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class EditEnrolleProfileScreen extends EnrolleProfileScreen {

    public EditEnrolleProfileScreen(ScreenNavigator screenNavigator, State state, Enrollee enrollee) {
        super(screenNavigator, state, enrollee);
        fillData();
    }

    private void fillData(){

        initialsView.getPatronymicInput().setText(getEnrolle().getPatronymic());
        initialsView.getFirstNameInput().setText(getEnrolle().getFirstName());
        initialsView.getSecondNameInput().setText(getEnrolle().getSecondName());

        dateBirthPickerView.setDate(getEnrolle().getDateBirth());

        passportView.getPasportNumberInput().setText(getEnrolle().getPassportNumber());

        phoneNumberView.getPhoneNumberView().setText(getEnrolle().getPhone());

        addressView.getCityInput().setText(getEnrolle().getCity());
        addressView.getStreetInput().setText(getEnrolle().getStreet());
        addressView.getHouseInput().setText(getEnrolle().getHouse());
        addressView.getFlatInput().setText(getEnrolle().getFlat());

        studyInfoView.getAttestatNumber().setText(getEnrolle().getAttestatNumber());
        studyInfoView.getSchoolName().setText(getEnrolle().getSchoolName());

        if (getEnrolle().getSpecialities() != null) {
            for (Speciality speciality : getEnrolle().getSpecialities()) {
                studyInfoView.getSpecialities().addText(AppTextUtils.getSpecialityNameWithCode(speciality));
            }
            enrolleProfileScreenPresenter.setSpecialities(getEnrolle().getSpecialities());
            studyInfoView.getInstitution().setText(getEnrolle().getSpecialities().get(0).getPulpit().getDepartment().getInstitution().getShortName());
        }

        studyInfoView.getStatus().select(Status.getByCode(getEnrolle().getStatus()).getName());

    }

}
