package ru.dmitry.selection_committee.gui.screens;

import com.vaadin.shared.ui.orderedlayout.HorizontalLayoutState;
import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.gui.Navigator;

public abstract class HorizontalLayoutScreen extends Screen {

    public HorizontalLayoutScreen(Navigator navigator, Component... components) {
        this(navigator);
        addComponents(components);
    }

    public HorizontalLayoutScreen(Navigator navigator) {
        super(navigator);
        setWidth("100%");
    }

    protected HorizontalLayoutState getState() {
        return (HorizontalLayoutState)super.getState();
    }

    protected HorizontalLayoutState getState(boolean markAsDirty) {
        return (HorizontalLayoutState)super.getState(markAsDirty);
    }

    public void addComponentsAndExpand(Component... components) {
        this.addComponents(components);
        if (this.getWidth() < 0.0F) {
            this.setWidth(100.0F, Unit.PERCENTAGE);
        }
        for (Component child : components) {
            child.setWidth(100.0F, Unit.PERCENTAGE);
            this.setExpandRatio(child, 1.0F);
        }

    }

}
