package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.gui.views.ListSelectView;
import ru.dmitry.selection_committee.resourse.R;

public class StudyInfoView extends VerticalLayout {

    private InputView<TextField> attestatNumber;

    private InputView<TextField> schoolName;

    private InputView<TextField> institution;

    private InputView<TextField> specialities;

    private InputView<TextField> status;

    private Button save;

    private Button signOut;

    private State state;

    private OnActionClickListener actionClickListener;

    public interface OnActionClickListener {
        void onInstitutionFieldClickListener();
    }

    public StudyInfoView(State state){
        this.state = state;
        setMargin(false);
        setWidth("80%");
        setup();
    }

    private void setup(){

        attestatNumber = new InputView<>(new TextField(), R.Strings.ATTESTAT_NUMBER);
        attestatNumber.addInputStyle("v-textfield-profile");

        schoolName = new InputView<>(new TextField(), R.Strings.SCHOOL_NAME);
        schoolName.addInputStyle("v-textfield-profile");

        institution = new InputView<>(new TextField(), R.Strings.INSTITUTION_HINT);
        institution.addInputStyle("v-textfield-profile");
        institution.setFocusChangeListener(new InputView.OnFocusChangeListener() {
            @Override
            public void onFocusChangeListener(boolean hasFocus) {
                if (hasFocus && actionClickListener != null){
                    actionClickListener.onInstitutionFieldClickListener();
                }
            }
        });

        specialities = new InputView<>(new TextField(), R.Strings.SPECIALITY_HINT);
        specialities.addInputStyle("v-textfield-profile");

        status = new InputView<>(new TextField(), R.Strings.STATUS);
        status.addInputStyle("v-textfield-profile");

        save = new Button(R.Strings.SAVE_OR_UPDATE);
        save.addStyleName("v-button-download-avatar");
        save.setWidth("50%");

        signOut = new Button(R.Strings.SIGN_OUT);
        signOut.addStyleName("v-button-delete-avatar");
        signOut.setWidth("50%");

        addComponents(attestatNumber, schoolName, institution, specialities, status, save, signOut);

        setComponentAlignment(save, Alignment.MIDDLE_CENTER);
        setComponentAlignment(signOut, Alignment.MIDDLE_CENTER);

        if (!state.equals(State.EDIT_ADMIN)){
            status.setVisible(false);
        }

    }

    public void setActionClickListener(OnActionClickListener actionClickListener) {
        this.actionClickListener = actionClickListener;
    }

    public enum State {
        REGISTER, EDIT, EDIT_ADMIN;
    }

}
