package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.ui.*;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.gui.views.ListSelectView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Status;

public class StudyInfoView extends VerticalLayout {

    private InputView<TextField> attestatNumber;

    private InputView<TextField> schoolName;

    private InputView<TextField> institution;

    private InputView<TextField> specialities;

    private ListSelectView status;

    private Button save;

    private Button signOut;

    private State state;

    private OnActionClickListener actionClickListener;

    private StudyDataChangeListener studyDataChangeListener;

    public interface StudyDataChangeListener {
        void onAttestatNumberChange(CharSequence text);
        void onSchoolChanged(CharSequence text);
        void onSpecialityChanged();
        void onStatusChanged(Status status);
    }

    public interface OnActionClickListener {
        void onInstitutionFieldClickListener();
        void onSpecialityFiledClickListener();
        void onSaveButtonClickListener();
        void onSignOutClickListener();
    }

    public StudyInfoView(State state){
        this.state = state;
        setMargin(false);
        setWidth("80%");
        setup();
    }

    private void setup(){

        attestatNumber = new InputView<>(new TextField(), R.Strings.ATTESTAT_NUMBER);
        attestatNumber.setTextChangeListener(this::onAttestaNumberChanged);
        attestatNumber.addInputStyle("v-textfield-profile");

        schoolName = new InputView<>(new TextField(), R.Strings.SCHOOL_NAME);
        schoolName.setTextChangeListener(this::onSchoolChanged);
        schoolName.addInputStyle("v-textfield-profile");

        HorizontalLayout institutionContainer = new HorizontalLayout();
        institutionContainer.setWidth("100%");
        institutionContainer.setMargin(false);
        institutionContainer.setSpacing(false);

        institution = new InputView<>(new TextField(), R.Strings.INSTITUTION_HINT);
        institution.setEnable(false);
        institution.addInputStyle("v-textfield-profile");

        Button addInstitutionButton = new Button(R.Strings.ADD);
        addInstitutionButton.addStyleName("v-button-add_institution");
        addInstitutionButton.addClickListener(this::onAddInstitutionButtonClick);

        institutionContainer.addComponents(institution, addInstitutionButton);
        institutionContainer.setComponentAlignment(institution, Alignment.TOP_LEFT);
        institutionContainer.setComponentAlignment(addInstitutionButton, Alignment.TOP_RIGHT);

        HorizontalLayout specialityContainer = new HorizontalLayout();
        specialityContainer.setWidth("100%");
        specialityContainer.setMargin(false);
        specialityContainer.setSpacing(false);

        specialities = new InputView<>(new TextField(), R.Strings.SPECIALITY_HINT);
        specialities.setEnable(false);
        specialities.addInputStyle("v-textfield-profile");

        Button addSpecialityButton = new Button(R.Strings.CHOOSE);
        addSpecialityButton.addStyleName("v-button-add_institution");
        addSpecialityButton.addClickListener(this::onAddSpecialityButtonClick);

        specialityContainer.addComponents(specialities, addSpecialityButton);
        specialityContainer.setComponentAlignment(specialities, Alignment.TOP_LEFT);
        specialityContainer.setComponentAlignment(addSpecialityButton, Alignment.TOP_RIGHT);

        status = new ListSelectView(R.Strings.STATUS);
        status.setData(Status.getStatusNames());
        status.setOnItemSelectedListener(this::onStatusItemSelected);

        save = new Button(R.Strings.SAVE_OR_UPDATE);
        save.addStyleName("v-button-download-avatar");
        save.addClickListener(this::onSaveButtonClick);
        save.setWidth("50%");

        signOut = new Button(R.Strings.SIGN_OUT);
        signOut.addStyleName("v-button-delete-avatar");
        signOut.addClickListener(this::onSignOutClick);
        signOut.setWidth("50%");

        addComponents(attestatNumber, schoolName, institutionContainer, specialityContainer, status, save, signOut);

        setComponentAlignment(save, Alignment.MIDDLE_CENTER);
        setComponentAlignment(signOut, Alignment.MIDDLE_CENTER);

        if (!state.equals(State.EDIT_ADMIN) && !state.equals(State.REGISTER_ADMIN)){
            status.setVisible(false);
        }

    }

    private void onAddSpecialityButtonClick(Button.ClickEvent clickEvent){
        if (actionClickListener != null){
            actionClickListener.onSpecialityFiledClickListener();
        }
    }

    private void onAddInstitutionButtonClick(Button.ClickEvent clickEvent){
        if (actionClickListener != null){
            actionClickListener.onInstitutionFieldClickListener();
        }
    }

    private void onAttestaNumberChanged(CharSequence text){
        if (studyDataChangeListener != null){
            studyDataChangeListener.onAttestatNumberChange(text);
        }
    }

    private void onSchoolChanged(CharSequence text){
        if (studyDataChangeListener != null){
            studyDataChangeListener.onSchoolChanged(text);
        }
    }

    private void onSpecilityChanged(){
        if (studyDataChangeListener != null){
            studyDataChangeListener.onSpecialityChanged();
        }
    }

    private void onSaveButtonClick(Button.ClickEvent clickEvent){
        if (actionClickListener != null){
            actionClickListener.onSaveButtonClickListener();
        }
    }

    private void onSignOutClick(Button.ClickEvent clickEvent){
        if (actionClickListener != null){
            actionClickListener.onSignOutClickListener();
        }
    }

    private void onStatusItemSelected(String status){
        if (studyDataChangeListener != null){
            studyDataChangeListener.onStatusChanged(Status.getByName(status));
        }
    }

    public void setStudyDataChangeListener(StudyDataChangeListener studyDataChangeListener) {
        this.studyDataChangeListener = studyDataChangeListener;
    }

    public void setActionClickListener(OnActionClickListener actionClickListener) {
        this.actionClickListener = actionClickListener;
    }

    public void setInstitutionText(String text){
        institution.setText(text);
        onSpecilityChanged();
    }

    public void addSpecialityText(String text){
        specialities.addText(text);
        onSpecilityChanged();
    }

    public InputView<TextField> getAttestatNumber() {
        return attestatNumber;
    }

    public InputView<TextField> getSchoolName() {
        return schoolName;
    }

    public InputView<TextField> getInstitution() {
        return institution;
    }

    public InputView<TextField> getSpecialities() {
        return specialities;
    }

    public ListSelectView getStatus() {
        return status;
    }
}
