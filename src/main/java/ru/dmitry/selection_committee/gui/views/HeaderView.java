package ru.dmitry.selection_committee.gui.views;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import ru.dmitry.selection_committee.resourse.R;

import java.util.List;

public abstract class HeaderView extends HorizontalLayout {

    private Label title;

    private HorizontalLayout actionViewContainer;

    public HeaderView(){
        setSpacing(false);
        addStyleName("header_main");
        setup();
    }

    private void setup(){
        title = new Label(R.Strings.PENGTU);
        title.addStyleName("v-label-heder_penzgtu");
        addComponent(title);
        setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        actionViewContainer = new HorizontalLayout();
        actionViewContainer.setSpacing(false);
        actionViewContainer.setMargin(false);
        actionViewContainer.addComponents(getActionComponents());
        addComponent(actionViewContainer);
        setComponentAlignment(actionViewContainer, Alignment.MIDDLE_RIGHT);
    }

    protected abstract Component[] getActionComponents();

}
