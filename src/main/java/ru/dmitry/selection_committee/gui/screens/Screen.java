package ru.dmitry.selection_committee.gui.screens;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import ru.dmitry.selection_committee.gui.Navigator;

import java.util.Collections;
import java.util.List;

public abstract class Screen extends AbstractOrderedLayout {

    private long screenDeep;

    private Navigator navigator;

    public Screen(Navigator navigator){
        this.navigator = navigator;
        addComponents(getComponents());
    }

    public void setScreenDeep(long screenDeep) {
        this.screenDeep = screenDeep;
    }

    private Component[] getComponents(){
        List<Component> componentList = getScreenComponents();
        Component[] components = new Component[componentList.size()];
        return componentList.toArray(components);
    }

    protected abstract List<Component> getScreenComponents();

}
