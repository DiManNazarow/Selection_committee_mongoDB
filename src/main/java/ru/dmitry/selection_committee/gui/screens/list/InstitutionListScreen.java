package ru.dmitry.selection_committee.gui.screens.list;

import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.list.filters.InstitutionFiltersList;
import ru.dmitry.selection_committee.gui.screens.list.mvp.InstitutionListScreenPresenter;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Institution;

import java.util.List;

public class InstitutionListScreen extends AbsListScreen<InstitutionListScreenPresenter, InstitutionFiltersList, Institution> {

    private final String URL = "institution_list";

    public InstitutionListScreen(ScreenNavigator screenNavigator){
        super(screenNavigator);
        screenPresenter.getAllInstitution();
    }

    @Override
    public void onSearchQueryTextChanged(String text) {
        screenPresenter.getInstitutionsFilterByName(text);
    }

    @Override
    public void onSearchIconClick() {
        screenPresenter.getInstitutionsFilterByName(listScreenHeader.getSearchQuery());
    }

    @Override
    public String getUrl(){
        return URL;
    }

    @Override
    public void onListReady(List<Institution> institutions) {
        listGrid.setItems(institutions);
    }

    @Override
    protected void addGridColumn() {
        listGrid.addColumn(Institution::getShortName).setCaption(R.Strings.SHORT_NAME).setResizable(true);
        listGrid.addColumn(Institution::getFullName).setCaption(R.Strings.FULL_NAME).setResizable(true);
        listGrid.addColumn(Institution::getCity).setCaption(R.Strings.CITY).setResizable(true);
        listGrid.addColumn(Institution::getStreet).setCaption(R.Strings.STREET).setResizable(true);
        listGrid.addColumn(Institution::getHome).setCaption(R.Strings.HOUSE).setResizable(true);
    }

    @Override
    protected InstitutionFiltersList getFiltersView() {
        InstitutionFiltersList institutionFiltersList = new InstitutionFiltersList();
        institutionFiltersList.setInstitutionFilterStateListener(new InstitutionFiltersList.OnInstitutionFilterStateListener() {
            @Override
            public void onInstitutionFilterApply(String city) {
                screenPresenter.getInstitutionsFilterByCity(city);
            }

            @Override
            public void onInstitutionFilterClear() {
                screenPresenter.getAllInstitution();
            }
        });
        return institutionFiltersList;
    }

    @Override
    protected InstitutionListScreenPresenter getScreenPresenter() {
        return new InstitutionListScreenPresenter(this, screenNavigator.getInstitutionService());
    }
}
