package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.views.ListWindow;
import ru.dmitry.selection_committee.resourse.R;

public class EnrolleProfileScreen extends CustomLayoutScreen implements StudyInfoView.OnActionClickListener {

    private final String URL = "enrolle_profile";

    public EnrolleProfileScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "enrolle_profile_screen");
        setSizeFull();
    }

    @Override
    protected void addComponents() {

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

        InitialsView initialsView = new InitialsView();
        addComponent(initialsView,"initials");

        DateBirthPickerView dateBirthPickerView = new DateBirthPickerView();
        addComponent(dateBirthPickerView, "date_birth");

        PassportView passportView = new PassportView();
        PhoneNumberView phoneNumberView = new PhoneNumberView();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.setMargin(false);
        horizontalLayout.setSpacing(false);
        horizontalLayout.addComponents(passportView, phoneNumberView);
        addComponent(horizontalLayout, "documents");

        AddressView addressView = new AddressView();
        addComponent(addressView, "address");

        StudyInfoView studyInfoView = new StudyInfoView(StudyInfoView.State.EDIT_ADMIN);
        studyInfoView.setActionClickListener(this);
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

    @Override
    public void onInstitutionFieldClickListener() {
        //TODO change
        if (!isWindowOpen){
            ListWindow listWindow = new ListWindow("Выберите учебное заведение", "Список учебных заведений");
            listWindow.addDataToList(screenNavigator.getInstitutionService().getInstitutionsNames());
            listWindow.setListItemSelectListener(new ListWindow.OnListItemSelectListener() {
                @Override
                public void onListItemSelected(String name) {
                    Notification.show(name);
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
}
