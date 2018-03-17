package ru.dmitry.selection_committee.gui.screens;

import com.vaadin.shared.ui.orderedlayout.VerticalLayoutState;
import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.gui.Navigator;

import java.util.List;

public class VerticalLayoutScreen extends Screen {

    public VerticalLayoutScreen(Navigator navigator) {
        super(navigator);
        this.setWidth("100%");
        this.setSpacing(true);
        this.setMargin(true);
    }

    @Override
    protected List<Component> getScreenComponents() {
        return null;
    }

    public VerticalLayoutScreen(Navigator navigator, Component... children) {
        this(navigator);
        addComponents(children);
    }

    protected VerticalLayoutState getState() {
        return (VerticalLayoutState)super.getState();
    }

    protected VerticalLayoutState getState(boolean markAsDirty) {
        return (VerticalLayoutState)super.getState(markAsDirty);
    }

    public void addComponentsAndExpand(Component... components) {
        this.addComponents(components);
        if (this.getHeight() < 0.0F) {
            this.setHeight(100.0F, Unit.PERCENTAGE);
        }
        for (Component child : components) {
            child.setHeight(100.0F, Unit.PERCENTAGE);
            this.setExpandRatio(child, 1.0F);
        }

    }

}
