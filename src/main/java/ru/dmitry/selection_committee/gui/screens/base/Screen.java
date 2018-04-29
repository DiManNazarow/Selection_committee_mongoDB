package ru.dmitry.selection_committee.gui.screens.base;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import ru.dmitry.selection_committee.gui.Navigator;

import java.util.Collections;
import java.util.List;

public abstract class Screen extends AbstractOrderedLayout {

    private Navigator navigator;

    public Screen(Navigator navigator){
        this.navigator = navigator;
        addComponents(getComponents());
    }

    private Component[] getComponents(){
        List<Component> componentList = getScreenComponents();
        Component[] components = new Component[componentList.size()];
        return componentList.toArray(components);
    }

    protected abstract List<Component> getScreenComponents();

}
