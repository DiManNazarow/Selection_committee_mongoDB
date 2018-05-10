package ru.dmitry.selection_committee.gui.screens.registration;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import ru.dmitry.selection_committee.gui.screens.profile.HeaderProfileView;
import ru.dmitry.selection_committee.resourse.R;

public class HeaderRegistrationView extends HeaderProfileView {

    public HeaderRegistrationView(){
        super();
    }

    @Override
    protected Component[] getActionComponents() {

        title = new Label(R.Strings.REGISTRATION);
        title.addStyleName("v-label-enrolle_title");

        return new Component[]{title};

    }

}
