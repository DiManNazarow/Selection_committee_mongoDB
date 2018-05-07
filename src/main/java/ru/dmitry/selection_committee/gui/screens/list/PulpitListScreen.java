package ru.dmitry.selection_committee.gui.screens.list;

import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.list.filters.PulpitFiltersList;
import ru.dmitry.selection_committee.gui.screens.list.mvp.PulpitListScreenPresenter;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Pulpit;

public class PulpitListScreen extends AbsListScreen<PulpitListScreenPresenter, PulpitFiltersList, Pulpit> {

    private final String URL = "pulpit_list";

    public PulpitListScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator);
        screenPresenter.getAllPulpit();
    }

    @Override
    protected void addGridColumn() {
        listGrid.addColumn(Pulpit::getShortName).setCaption(R.Strings.SHORT_NAME).setResizable(true);
        listGrid.addColumn(Pulpit::getFullName).setCaption(R.Strings.FULL_NAME).setResizable(true);
        listGrid.addColumn(pulpit -> pulpit.getDepartment().getShortName()).setCaption(R.Strings.DEPARTMENT).setResizable(true);
        listGrid.addColumn(pulpit -> pulpit.getDepartment().getInstitution().getShortName()).setCaption(R.Strings.INSTITUTION).setResizable(true);
    }

    @Override
    protected PulpitFiltersList getFiltersView() {
        PulpitFiltersList pulpitFiltersList = new PulpitFiltersList();
        pulpitFiltersList.setPulpitFilterStateListener(new PulpitFiltersList.OnPulpitFilterStateListener() {
            @Override
            public void onPulpitFilterApply(String institution, String department) {
                screenPresenter.getPulpitFilteredByInstitutionAndDeparture(institution, department);
            }

            @Override
            public void onPulpitFilterClear() {
                screenPresenter.getAllPulpit();
            }
        });
        return pulpitFiltersList;
    }

    @Override
    protected PulpitListScreenPresenter getScreenPresenter() {
        return new PulpitListScreenPresenter(this, screenNavigator.getPulpitService());
    }

    @Override
    public void onSearchQueryTextChanged(String text) {

    }

    @Override
    public void onSearchIconClick() {
        screenPresenter.getPulpitFilteredByName(listScreenHeader.getSearchQuery());
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
