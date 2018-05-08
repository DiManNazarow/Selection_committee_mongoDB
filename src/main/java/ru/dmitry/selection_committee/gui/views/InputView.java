package ru.dmitry.selection_committee.gui.views;

import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class InputView<I extends TextField> extends VerticalLayout implements View {

    private final String EMPTY_FIELD_ERROR = R.Strings.Errors.EMPTY_FIELD;

    private boolean isError = false;

    private String text;

    private I inputField;

    private Label errorTextLabel;

    private OnTextChangeListener textChangeListener;

    private OnFocusChangeListener focusChangeListener;

    private boolean isFocus = false;

    public interface OnTextChangeListener {
        void onTextChanged(CharSequence charSequence);
    }

    public interface OnFocusChangeListener {
        void onFocusChangeListener(boolean hasFocus);
    }

    public InputView(I inputField, String hint){
        this.inputField = inputField;
        init(hint);
    }

    private void init(String hint){
        setMargin(false);
        setSpacing(false);
        inputField.setWidth("100%");
        inputField.setPlaceholder(hint);

        inputField.addFocusListener(new FieldEvents.FocusListener() {
            @Override
            public void focus(FieldEvents.FocusEvent focusEvent) {
                changeFocusState(true);
            }
        });
        inputField.addBlurListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent blurEvent) {
                changeFocusState(false);
            }
        });

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

    private void changeFocusState(boolean newState){
        if (isFocus != newState){
            if (focusChangeListener != null){
                focusChangeListener.onFocusChangeListener(newState);
            }
            isFocus = newState;
        }
    }

    public void setTextChangeListener(OnTextChangeListener textChangeListener) {
        this.textChangeListener = textChangeListener;
    }

    public void showEmptyTextError(){
        showError(EMPTY_FIELD_ERROR);
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

    public void addInputStyle(String name){
        inputField.addStyleName(name);
    }

    public void removeInputStyle(String name){
        inputField.removeStyleName(name);
    }

    public void setFocusChangeListener(OnFocusChangeListener focusChangeListener) {
        this.focusChangeListener = focusChangeListener;
    }
}
