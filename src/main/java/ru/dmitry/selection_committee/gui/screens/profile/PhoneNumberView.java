package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.resourse.R;

public class PhoneNumberView extends VerticalLayout {

    private InputView<TextField> phoneNumberView;

    private String phone;

    private OnPhoneNumberChangeListener phoneNumberChangeListener;

    public interface OnPhoneNumberChangeListener {
        void onPhoneTextChanged(CharSequence text);
    }

    public PhoneNumberView(){
        setMargin(false);
        setSpacing(false);
        setWidth("90%");
        setup();
    }

    private void setup(){

        phoneNumberView = new InputView<>(new TextField(), R.Strings.PHONE);
        phoneNumberView.addInputStyle("v-textfield-profile");
        phoneNumberView.setTextChangeListener(this::onPhoneNumberChanged);
        addComponent(phoneNumberView);

    }

    private void onPhoneNumberChanged(CharSequence text){
        phone = text.toString();
        if (phoneNumberChangeListener != null){
            phoneNumberChangeListener.onPhoneTextChanged(text);
        }
    }

    public void setPhoneNumberChangeListener(OnPhoneNumberChangeListener phoneNumberChangeListener) {
        this.phoneNumberChangeListener = phoneNumberChangeListener;
    }

    public String getPhone() {
        return phone;
    }

    public InputView<TextField> getPhoneNumberView() {
        return phoneNumberView;
    }
}
