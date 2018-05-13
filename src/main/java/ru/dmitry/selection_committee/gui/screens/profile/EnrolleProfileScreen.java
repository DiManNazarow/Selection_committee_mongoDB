package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.screens.list.EnrolleListScreen;
import ru.dmitry.selection_committee.gui.screens.profile.mvp.EnrolleProfileScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.profile.mvp.EnrolleProfileScreenView;
import ru.dmitry.selection_committee.gui.views.ListWindow;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.models.Status;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.util.ArrayList;
import java.util.List;

public class EnrolleProfileScreen extends CustomLayoutScreen<State> implements StudyInfoView.OnActionClickListener,
        EnrolleProfileScreenView, InitialsView.OnInitialsChangeListener,
        DateBirthPickerView.OnDateChangeListener, PassportView.DocumentNumberChangeListener, PhoneNumberView.OnPhoneNumberChangeListener,
        AddressView.AddressChangeListener, StudyInfoView.StudyDataChangeListener {

    private final String URL = "enrolle_profile";

    protected InitialsView initialsView;

    protected DateBirthPickerView dateBirthPickerView;

    protected PassportView passportView;

    protected PhoneNumberView phoneNumberView;

    protected AddressView addressView;

    protected StudyInfoView studyInfoView;

    protected EnrolleProfileScreenPresenter enrolleProfileScreenPresenter;

    public EnrolleProfileScreen(ScreenNavigator screenNavigator, State state, Enrollee enrollee) {
        super(screenNavigator, state,"enrolle_profile_screen");
        enrolleProfileScreenPresenter = new EnrolleProfileScreenPresenter(this, screenNavigator.getUserServices(), enrollee);
        setSizeFull();
    }

    @Override
    protected void setupComponents(State data) {

        HeaderProfileView headerProfileView = new HeaderProfileView();
        addComponent(headerProfileView, "profile_header_layout");

        Image avatar = new Image(null, new ThemeResource("img/person-flat.png"));
        avatar.setHeight(135, Unit.PIXELS);
        avatar.setWidth(135, Unit.PIXELS);
        addComponent(avatar, "avatar_image");

        Button downloadButton = new Button(R.Strings.DOWNLOAD_AVATAR);
        downloadButton.addStyleName("v-button-download-avatar");
        //downloadButton.addStyleName("v-button-caption-download");
        addComponent(downloadButton, "download_button");

        Button deleteButton = new Button(R.Strings.DELETE_AVATAR);
        deleteButton.addStyleName("v-button-delete-avatar");
        //deleteButton.addStyleName("v-button-caption-delete");
        addComponent(deleteButton, "delete_button");

        initialsView = new InitialsView();
        initialsView.setInitialsChangeListener(this);
        addComponent(initialsView,"initials");

        dateBirthPickerView = new DateBirthPickerView();
        dateBirthPickerView.setDateChangeListener(this);
        addComponent(dateBirthPickerView, "date_birth");

        passportView = new PassportView();
        passportView.setDocumentNumberChangeListener(this);
        phoneNumberView = new PhoneNumberView();
        phoneNumberView.setPhoneNumberChangeListener(this);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setMargin(false);
        horizontalLayout.setSpacing(false);
        horizontalLayout.addComponents(passportView, phoneNumberView);
        addComponent(horizontalLayout, "documents");

        addressView = new AddressView();
        addressView.setAddressChangeListener(this);
        addComponent(addressView, "address");

        studyInfoView = new StudyInfoView(data);
        studyInfoView.setActionClickListener(this);
        studyInfoView.setStudyDataChangeListener(this);
        addComponent(studyInfoView, "study_data");

        Label personalDataTitle = new Label(R.Strings.PERSONAL_DATA);
        personalDataTitle.addStyleName("v-personal-data-title");
        addComponent(personalDataTitle, "personal_data_title");

        Label studyData = new Label(R.Strings.STUDY_DATA);
        studyData.addStyleName("v-personal-data-title");
        addComponent(studyData, "study_data_title");

    }

    @Override
    public String getUrl() {
        return URL;
    }

    private boolean isWindowOpen = false;

    private Institution institution;

    @Override
    public void onInstitutionFieldClickListener() {
        if (!isWindowOpen){
            ListWindow listWindow = new ListWindow("Выберите учебное заведение", "Список учебных заведений");
            listWindow.addDataToList(screenNavigator.getInstitutionService().getInstitutionsNames());
            listWindow.setListItemSelectListener(new ListWindow.OnListItemSelectListener() {
                @Override
                public void onListItemSelected(String name) {
                    institution = screenNavigator.getInstitutionService().findByFullName(name);
                    if (institution != null) {
                        studyInfoView.setInstitutionText(institution.getShortName());
                    } else {
                        Notification.show("Учебное заведение не найдено");
                    }
                }
            });
            listWindow.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent closeEvent) {
                    isWindowOpen = false;
                }
            });
            isWindowOpen = true;
            UI.getCurrent().addWindow(listWindow);
        }
    }

    private List<Speciality> specialities = new ArrayList<>();

    @Override
    public void onSpecialityFiledClickListener() {
        if (!isWindowOpen){
            ListWindow listWindow = new ListWindow("Выберите специальность", "Список специальностей");
            listWindow.addDataToList(institution != null ? screenNavigator.getSpecialityService().getSpecialityNamesWithCodeFilteredByInstitution(institution) : screenNavigator.getSpecialityService().getSpecialityNamesWithCode());
            listWindow.setListItemSelectListener(new ListWindow.OnListItemSelectListener() {
                @Override
                public void onListItemSelected(String name) {
                    Speciality speciality = screenNavigator.getSpecialityService().findByCode(name.split("\\s+")[0]);
                    if (speciality != null){
                        specialities.add(speciality);
                        studyInfoView.addSpecialityText(String.format("%s %s", speciality.getCode(), speciality.getName()));
                    } else {
                        Notification.show("Специальность не найдена");
                    }
                }
            });
            listWindow.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent closeEvent) {
                    isWindowOpen = false;
                }
            });
            isWindowOpen = true;
            UI.getCurrent().addWindow(listWindow);
        }
    }

    @Override
    public void onSaveButtonClickListener() {
        enrolleProfileScreenPresenter.setDateBirth(dateBirthPickerView.getDate());
        enrolleProfileScreenPresenter.save();
    }

    @Override
    public void onSignOutClickListener() {
        screenNavigator.signOut();
    }

    @Override
    public void onFirstNameEmpty() {
        initialsView.getFirstNameInput().showEmptyTextError();
    }

    @Override
    public void onSecondNameEmpty() {
        initialsView.getSecondNameInput().showEmptyTextError();
    }

    @Override
    public void onPatronymicEmpty() {
        initialsView.getPatronymicInput().showEmptyTextError();
    }

    @Override
    public void onDateBirthEmpty() {
        Notification.show("Укажите дату рождения");
    }

    @Override
    public void onPasportNameEmpty() {
        passportView.getPasportNumberInput().showEmptyTextError();
    }

    @Override
    public void onPhoneNumberEmpty() {
        phoneNumberView.getPhoneNumberView().showEmptyTextError();
    }

    @Override
    public void onCityEmpty() {
        addressView.getStreetInput().showEmptyTextError();
    }

    @Override
    public void onStreetEmpty() {
        addressView.getStreetInput().showEmptyTextError();
    }

    @Override
    public void onHouseEmpty() {
        addressView.getHouseInput().showEmptyTextError();
    }

    @Override
    public void onFlatEmpty() {

    }

    @Override
    public void onAttestatEmpty() {
        studyInfoView.getAttestatNumber().showEmptyTextError();
    }

    @Override
    public void onSchoolEmpty() {
        studyInfoView.getSchoolName().showEmptyTextError();
    }

    @Override
    public void onSpecialityNotChosen() {
        Notification.show("Выберите специальность");
    }

    @Override
    public void onProfileSuccessEdit() {
        EnrolleListScreen enrolleListScreen = new EnrolleListScreen(screenNavigator, State.ENROLLE_LIST_USER);
        screenNavigator.openScreen(enrolleListScreen.getUrl(), enrolleListScreen);
    }

    @Override
    public void onProfileEditFailed() {
        Notification.show("Ошибка сохранения профиля");
    }

    @Override
    public void onSignOut() {
        screenNavigator.signOut();
    }

    @Override
    public void onFirstNameChanged(CharSequence firstName) {
        enrolleProfileScreenPresenter.setFirstName(firstName.toString());
    }

    @Override
    public void onSecondNameChanged(CharSequence secondName) {
        enrolleProfileScreenPresenter.setSecondName(secondName.toString());
    }

    @Override
    public void onPatronymicChanged(CharSequence patronymic) {
        enrolleProfileScreenPresenter.setPatronymic(patronymic.toString());
    }

    @Override
    public void onDateChange(String date) {
        enrolleProfileScreenPresenter.setDateBirth(date);
    }

    @Override
    public void onDocumentNumberChanged(CharSequence text) {
        enrolleProfileScreenPresenter.setPasportNumber(text.toString());
    }

    @Override
    public void onPhoneTextChanged(CharSequence text) {
        enrolleProfileScreenPresenter.setPhoneNumber(text.toString());
    }

    @Override
    public void onCityChanged(CharSequence text) {
        enrolleProfileScreenPresenter.setCity(text.toString());
    }

    @Override
    public void onStreetChanged(CharSequence text) {
        enrolleProfileScreenPresenter.setStreet(text.toString());
    }

    @Override
    public void onHouseChanged(CharSequence text) {
        enrolleProfileScreenPresenter.setHouse(text.toString());
    }

    @Override
    public void onFlatChanged(CharSequence text) {
        enrolleProfileScreenPresenter.setFlat(text.toString());
    }

    @Override
    public void onAttestatNumberChange(CharSequence text) {
        enrolleProfileScreenPresenter.setAttestatNumber(text.toString());
    }

    @Override
    public void onSchoolChanged(CharSequence text) {
        enrolleProfileScreenPresenter.setSchoolName(text.toString());
    }

    @Override
    public void onSpecialityChanged() {
        enrolleProfileScreenPresenter.setSpecialities(specialities);
    }

    @Override
    public void onStatusChanged(Status status) {
        enrolleProfileScreenPresenter.setStatus(status);
    }

    public Enrollee getEnrolle(){
        return enrolleProfileScreenPresenter.getEnrollee();
    }

}
