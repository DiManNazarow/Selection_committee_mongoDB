package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.ui.renderers.ButtonRenderer;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.list.filters.EnrolleFiltersList;
import ru.dmitry.selection_committee.gui.screens.list.mvp.EnrolleListScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.profile.EditEnrolleProfileScreen;
import ru.dmitry.selection_committee.gui.screens.profile.State;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Role;
import ru.dmitry.selection_committee.utils.AppTextUtils;
import ru.dmitry.selection_committee.utils.DateUtils;

public class EnrolleListScreen extends AbsListScreen<EnrolleListScreenPresenter, EnrolleFiltersList, Enrollee>{

    private final String URL = "enrolle_list";

    private EnrolleFiltersList enrolleFiltersList;

    public EnrolleListScreen(ScreenNavigator screenNavigator, State state) {
        super(screenNavigator, state);
        getEnrollList(state);
        listScreenHeader.setSearchPlaceholder("ФИО");
    }

    private void getEnrollList(State state){
        if (state.equals(State.ENROLLE_LIST_ADMIN)){
            screenPresenter.getAllEnrolle();
        } else {
            screenPresenter.getAllEnrolleForYear(DateUtils.getCurrentYear());
        }
    }

    @Override
    protected void addGridColumn(Object data) {
        listGrid.addColumn(Enrollee::getFirstName).setCaption(R.Strings.FIRST_NAME).setResizable(true);
        listGrid.addColumn(Enrollee::getSecondName).setCaption(R.Strings.SECOND_NAME).setResizable(true);
        listGrid.addColumn(Enrollee::getPatronymic).setCaption(R.Strings.PATRONYMIC).setResizable(true);
        listGrid.addColumn(AppTextUtils::getSpecialityNames).setCaption(R.Strings.SPECIALITY).setResizable(true);
        listGrid.addColumn(enrollee -> enrollee.getSpecialities().get(0).getPulpit().getDepartment().getInstitution().getShortName()).setCaption(R.Strings.INSTITUTION).setResizable(true);
        if (screenNavigator.getAuthUser().getRole() == Role.ADMIN.getRoleCode()) {
            listGrid.addColumn(institution -> "Удалить", new ButtonRenderer<>(rendererClickEvent -> {
                screenPresenter.delete(rendererClickEvent.getItem());
            }));
        }
    }

    @Override
    protected EnrolleFiltersList getFiltersView(Object data) {
        enrolleFiltersList = new EnrolleFiltersList((State) data);
        enrolleFiltersList.setEnrolleFiltersStateListener(new EnrolleFiltersList.EnrolleFiltersStateListener() {
            @Override
            public void onEnrolleFiltersApply(String institution, String speciality, int year) {
                screenPresenter.filter(institution, speciality, year);
            }

            @Override
            public void onEnrolleFilterClear() {
                State state = (State)data;
                if (state.equals(State.ENROLLE_LIST_ADMIN)){
                    screenPresenter.getAllEnrolle();
                } else {
                    screenPresenter.getAllEnrolleForYear(DateUtils.getCurrentYear());
                }
            }
        });
        return enrolleFiltersList;
    }

    @Override
    protected EnrolleListScreenPresenter getScreenPresenter(Object data) {
        return new EnrolleListScreenPresenter(this, screenNavigator.getUserServices());
    }

    @Override
    public void onSearchQueryTextChanged(String text) {

    }

    @Override
    public void onSearchIconClick() {
        enrolleFiltersList.clearField();
        screenPresenter.getEnrolleFilteredByName(listScreenHeader.getSearchQuery());
    }

    @Override
    public void onItemClicked(Enrollee enrollee) {
        if (screenNavigator.getAuthUser().getRole() == Role.ADMIN.getRoleCode()){
            EditEnrolleProfileScreen editEnrolleProfileScreen = new EditEnrolleProfileScreen(screenNavigator, State.EDIT_ADMIN, enrollee);
            screenNavigator.openScreen(editEnrolleProfileScreen.getUrl(), editEnrolleProfileScreen);
        }
    }

    @Override
    public String getUrl() {
        return URL;
    }
}
