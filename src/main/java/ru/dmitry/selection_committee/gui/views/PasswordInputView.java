package ru.dmitry.selection_committee.gui.views;

import com.vaadin.ui.PasswordField;

public class PasswordInputView extends AuthInputView<PasswordField> {

    public PasswordInputView(String hint) {
        super(new PasswordField(), hint);
    }
}
