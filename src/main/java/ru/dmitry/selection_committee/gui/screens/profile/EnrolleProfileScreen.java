package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.resourse.R;

public class EnrolleProfileScreen extends CustomLayoutScreen {

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
}
