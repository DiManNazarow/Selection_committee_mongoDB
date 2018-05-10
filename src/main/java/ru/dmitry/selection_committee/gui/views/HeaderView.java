package ru.dmitry.selection_committee.gui.views;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import ru.dmitry.selection_committee.resourse.R;

import java.util.List;

public abstract class HeaderView<T> extends HorizontalLayout {

    protected Label title;

    protected HorizontalLayout actionViewContainer;

    protected T model;

    public HeaderView(){
        setSpacing(false);
        addStyleName("header_main");
        setup();
    }

    public HeaderView(T model){
        this.model = model;
        setSpacing(false);
        addStyleName("header_main");
        setup();
    }

    protected void setup(){
        title = new Label(R.Strings.PENGTU);
        title.addStyleName("v-label-header_penzgtu");
        addComponent(title);
        setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        actionViewContainer = new HorizontalLayout();
        actionViewContainer.setMargin(false);
        actionViewContainer.addComponents(getActionComponents());
        addComponent(actionViewContainer);
        setComponentAlignment(actionViewContainer, Alignment.MIDDLE_RIGHT);
        setComponentsAlign();
    }

    protected void setComponentsAlign(){

    }

    protected abstract Component[] getActionComponents();

}
