package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.gui.views.HeaderSearchView;
import ru.dmitry.selection_committee.gui.views.HeaderView;
import ru.dmitry.selection_committee.resourse.R;

public class ListScreenHeader extends HeaderView {

    @Override
    protected Component[] getActionComponents() {
        HeaderSearchView headerSearchView = new HeaderSearchView(R.Strings.CITY_FILTER);
        return new Component[]{headerSearchView};
    }

}
