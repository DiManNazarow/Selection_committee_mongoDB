package ru.dmitry.selection_committee.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.util.Locale;

public abstract class AuthInputView<I extends TextField> extends VerticalLayout implements View {

    private final String EMPTY_FIELD_ERROR = R.Strings.Errors.EMPTY_FIELD;

    private final String WRONG_AUTH_DATA_ERROR = R.Strings.Errors.AUTH_ERROR;

    private boolean isError = false;

    private String text;

    private I inputField;

    private Label errorTextLabel;

    private OnTextChangeListener textChangeListener;

    public interface OnTextChangeListener{
        void onTextChanged(CharSequence charSequence);
    }

    public AuthInputView(I inputField, String hint){
        this.inputField = inputField;
        init(hint);
    }

    private void init(String hint){
        setMargin(false);
        inputField.setWidth("100%");
        inputField.setPlaceholder(hint);
        inputField.addValueChangeListener(event -> {
            text = event.getValue();
            if (textChangeListener != null){
                textChangeListener.onTextChanged(text);
            }
        });
        errorTextLabel = new Label();
        errorTextLabel.setWidth("100%");
        errorTextLabel.addStyleName("v-label-auth_error");
        addComponents(inputField, errorTextLabel);
    }

    public void setTextChangeListener(OnTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public void showEmptyTextError(){
        showError(EMPTY_FIELD_ERROR);
    }

    public void showWrongAuthDataError(){
        showError(WRONG_AUTH_DATA_ERROR);
    }

    public void showError(CharSequence error){
        isError = true;
        errorTextLabel.setValue(error.toString());
    }

    public void hideError(){
        errorTextLabel.setValue("");
        isError = false;
    }

    public void clear(){
        inputField.clear();
        errorTextLabel.setValue("");
        text = null;
        isError = false;
    }

    public boolean isErrorShow(){
        return isError;
    }

    public boolean isTextEmpty(){
        return AppTextUtils.isTextEmpty(text);
    }

}
