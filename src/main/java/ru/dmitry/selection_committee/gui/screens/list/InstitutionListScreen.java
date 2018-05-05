package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.server.models.Institution;

public class InstitutionListScreen extends AbsListScreen {

    private final String URL = "institution_list";

    private Grid<Institution> institutionGrid;

    public InstitutionListScreen(ScreenNavigator screenNavigator){
        super(screenNavigator);
        setSizeFull();
    }

    protected void addComponents(){
        super.addComponents();

        institutionGrid = new Grid<>(Institution.class);
        institutionGrid.setSizeFull();
        institutionGrid.setItems(screenNavigator.getInstitutionService().getAll());
        addComponent(institutionGrid, "list_layout");

    }

    public String getUrl(){
        return URL;
    }

}
