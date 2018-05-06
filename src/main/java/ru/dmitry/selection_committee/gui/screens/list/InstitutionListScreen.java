package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.list.filters.InstitutionFiltersList;
import ru.dmitry.selection_committee.gui.screens.list.mvp.InstitutionListScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.list.mvp.InstitutionListScreenView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Institution;

import java.util.List;

public class InstitutionListScreen extends AbsListScreen implements InstitutionListScreenView {

    private final String URL = "institution_list";

    private Grid<Institution> institutionGrid;

    private InstitutionFiltersList institutionFiltersList;

    private InstitutionListScreenPresenter institutionListScreenPresenter;

    public InstitutionListScreen(ScreenNavigator screenNavigator){
        super(screenNavigator);
        institutionListScreenPresenter = new InstitutionListScreenPresenter(this, screenNavigator.getInstitutionService());
        institutionListScreenPresenter.getAllInstitution();
        setSizeFull();
    }

    protected void addComponents(){
        super.addComponents();

        institutionGrid = new Grid<>();
        institutionGrid.setSizeFull();
        setupGridColumn();
        addComponent(institutionGrid, "list_layout");

        institutionFiltersList = new InstitutionFiltersList();
        institutionFiltersList.setInstitutionFilterStateListener(new InstitutionFiltersList.OnInstitutionFilterStateListener() {
            @Override
            public void onInstitutionFilterApply(String city) {
                institutionListScreenPresenter.getInstitutionsFilterByCity(city);
            }

            @Override
            public void onInstitutionFilterClear() {
                institutionListScreenPresenter.getAllInstitution();
            }
        });
        addComponent(institutionFiltersList, "filter_layout");

    }

    @Override
    public void onSearchQueryTextChanged(String text) {
        institutionListScreenPresenter.getInstitutionsFilterByName(text);
    }

    @Override
    public void onSearchIconClick() {
        institutionListScreenPresenter.getInstitutionsFilterByName(listScreenHeader.getSearchQuery());
    }

    private void setupGridColumn(){
        institutionGrid.addColumn(Institution::getShortName).setCaption(R.Strings.SHORT_NAME).setResizable(true);
        institutionGrid.addColumn(Institution::getFullName).setCaption(R.Strings.FULL_NAME).setResizable(true);
        institutionGrid.addColumn(Institution::getCity).setCaption(R.Strings.CITY).setResizable(true);
        institutionGrid.addColumn(Institution::getStreet).setCaption(R.Strings.STREET).setResizable(true);
        institutionGrid.addColumn(Institution::getHome).setCaption(R.Strings.HOUSE).setResizable(true);
    }


    public String getUrl(){
        return URL;
    }

    @Override
    public void onInstitutionListReady(List<Institution> institutions) {
        institutionGrid.setItems(institutions);
    }
}
