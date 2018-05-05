package ru.dmitry.selection_committee.gui.screens.list;

import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;

public abstract class AbsListScreen extends CustomLayoutScreen {

    public AbsListScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "list_screen");
    }

    protected void addComponents(){

        ListScreenHeader listScreenHeader = new ListScreenHeader();
        listScreenHeader.setSizeFull();
        addComponent(listScreenHeader, "header_layout");

    }

}
