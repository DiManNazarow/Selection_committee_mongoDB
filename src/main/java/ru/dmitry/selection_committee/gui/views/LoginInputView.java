package ru.dmitry.selection_committee.gui.views;

import com.vaadin.ui.TextField;

public class LoginInputView extends AuthInputView<TextField> {

    public LoginInputView(String hint) {
        super(new TextField(), hint);
    }
}
