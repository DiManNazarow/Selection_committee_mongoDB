package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.ui.renderers.ButtonRenderer;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.list.filters.SpecialityFiltersList;
import ru.dmitry.selection_committee.gui.screens.list.mvp.SpecialityListScreenPresenter;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Speciality;

public class SpecialityListScreen extends AbsListScreen<SpecialityListScreenPresenter, SpecialityFiltersList, Speciality> {

    private final String URL = "speciality_list";

    public SpecialityListScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator);
        screenPresenter.getAllSpecialities();
    }

    @Override
    protected void addGridColumn(Object data) {
        listGrid.addColumn(Speciality::getName).setCaption(R.Strings.FULL_NAME).setResizable(true);
        listGrid.addColumn(Speciality::getCode).setCaption(R.Strings.CODE_HINT).setResizable(true);
        listGrid.addColumn(speciality -> speciality.getPulpit().getDepartment().getShortName()).setCaption(R.Strings.DEPARTMENT).setResizable(true);
        listGrid.addColumn(speciality -> speciality.getPulpit().getDepartment().getInstitution().getShortName()).setCaption(R.Strings.INSTITUTION).setResizable(true);
        listGrid.addColumn(institution -> "Удалить", new ButtonRenderer<>(rendererClickEvent -> {
            screenPresenter.delete(rendererClickEvent.getItem());
        }));
    }

    @Override
    protected SpecialityFiltersList getFiltersView(Object data) {
        SpecialityFiltersList specialityFiltersList = new SpecialityFiltersList();
        specialityFiltersList.setSpecialityFilterStateListener(new SpecialityFiltersList.OnSpecialityFilterStateListener() {
            @Override
            public void onSpecialityFilterApply(String institution, String department, String pulpit) {
                screenPresenter.getSpecialitiesFilteredByInstitutionAndDepartmentAndPulpit(institution, department, pulpit);
            }

            @Override
            public void onSpecialityFilterClear() {
                screenPresenter.getAllSpecialities();
            }
        });
        return specialityFiltersList;
    }

    @Override
    protected SpecialityListScreenPresenter getScreenPresenter(Object data) {
        return new SpecialityListScreenPresenter(this, screenNavigator.getSpecialityService());
    }

    @Override
    public void onSearchQueryTextChanged(String text) {

    }

    @Override
    public void onSearchIconClick() {
        screenPresenter.getSpecialitiesFilteredByName(listScreenHeader.getSearchQuery());
    }

    @Override
    public void onItemClicked(Speciality speciality) {

    }

    @Override
    public String getUrl() {
        return URL;
    }
}
