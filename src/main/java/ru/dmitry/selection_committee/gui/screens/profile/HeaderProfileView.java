package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import ru.dmitry.selection_committee.gui.views.HeaderView;
import ru.dmitry.selection_committee.resourse.R;

public class HeaderProfileView extends HeaderView {

    private Label title;

    public HeaderProfileView(){
        setSizeFull();
    }

    @Override
    protected Component[] getActionComponents() {

        title = new Label(R.Strings.ENROLLE);
        title.addStyleName("v-label-enrolle_title");

        return new Component[]{title};
    }

    protected void setup(){
        super.setup();
        actionViewContainer.setSizeFull();
        actionViewContainer.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);
    }

}

