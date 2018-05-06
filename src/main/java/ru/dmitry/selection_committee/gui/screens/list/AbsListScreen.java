package ru.dmitry.selection_committee.gui.screens.list;

import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;

public abstract class AbsListScreen extends CustomLayoutScreen {

    protected  ListScreenHeader listScreenHeader;

    public AbsListScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "list_screen");
    }

    protected void addComponents(){

        listScreenHeader = new ListScreenHeader();
        listScreenHeader.setSizeFull();
        listScreenHeader.setHeaderActionProcessListener(new ListScreenHeader.OnHeaderActionProcessListener() {
            @Override
            public void onSearchQueryChange(String text) {
               onSearchQueryTextChanged(text);
            }

            @Override
            public void onSearchIconClick() {
                AbsListScreen.this.onSearchIconClick();
            }

            @Override
            public void onProfileClick() {

            }
        });
        addComponent(listScreenHeader, "header_layout");

    }

    public abstract void onSearchQueryTextChanged(String text);

    public abstract void onSearchIconClick();

}
