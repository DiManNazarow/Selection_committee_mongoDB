package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.resourse.R;

public class InitialsView extends VerticalLayout {

    private InputView<TextField> firstNameInput;

    private InputView<TextField> secondNameInput;

    private InputView<TextField> patronymicInput;

    private String firstName;

    private String secondName;

    private String patronymic;

    private OnInitialsChangeListener initialsChangeListener;

    public interface OnInitialsChangeListener {
        void onFirstNameChanged(CharSequence firstName);
        void onSecondNameChanged(CharSequence secondName);
        void onPatronymicChanged(CharSequence patronymic);
    }

    public InitialsView() {
        setWidth("80%");
        setHeight("80%");
        setSpacing(false);
        setMargin(false);
        setup();
    }

    private void setup(){
        firstNameInput = new InputView<>(new TextField(), R.Strings.FIRST_NAME);
        firstNameInput.addInputStyle("v-textfield-profile");
        firstNameInput.setTextChangeListener(this::onFirstNameChanged);
        secondNameInput = new InputView<>(new TextField(), R.Strings.SECOND_NAME);
        secondNameInput.addInputStyle("v-textfield-profile");
        secondNameInput.setTextChangeListener(this::onSecondNameChanged);
        patronymicInput = new InputView<>(new TextField(), R.Strings.PATRONYMIC);
        patronymicInput.addInputStyle("v-textfield-profile");
        patronymicInput.setTextChangeListener(this::onPatronymicChanged);
        addComponents(firstNameInput, secondNameInput, patronymicInput);
    }

    private void onFirstNameChanged(CharSequence text){
        firstName = text.toString();
        if (initialsChangeListener != null){
            initialsChangeListener.onFirstNameChanged(text);
        }
    }

    private void onSecondNameChanged(CharSequence text){
        secondName = text.toString();
        if (initialsChangeListener != null){
            initialsChangeListener.onSecondNameChanged(text);
        }
    }

    private void onPatronymicChanged(CharSequence text){
        patronymic = text.toString();
        if (initialsChangeListener != null){
            initialsChangeListener.onPatronymicChanged(text);
        }
    }

    public void setInitialsChangeListener(OnInitialsChangeListener initialsChangeListener) {
        this.initialsChangeListener = initialsChangeListener;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public InputView<TextField> getFirstNameInput() {
        return firstNameInput;
    }

    public InputView<TextField> getSecondNameInput() {
        return secondNameInput;
    }

    public InputView<TextField> getPatronymicInput() {
        return patronymicInput;
    }
}
