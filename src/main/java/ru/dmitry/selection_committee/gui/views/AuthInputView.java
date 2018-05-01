package ru.dmitry.selection_committee.gui.views;

import com.vaadin.ui.TextField;
import ru.dmitry.selection_committee.resourse.R;

public class AuthInputView<I extends TextField> extends InputView<I> {

    private final String WRONG_AUTH_DATA_ERROR = R.Strings.Errors.AUTH_ERROR;

    public AuthInputView(I inputField, String hint) {
        super(inputField, hint);
    }

    public void showWrongAuthDataError(){
        showError(WRONG_AUTH_DATA_ERROR);
    }

}
