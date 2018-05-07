package ru.dmitry.selection_committee.gui.screens.list;

import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.list.filters.DepartmentFiltersList;
import ru.dmitry.selection_committee.gui.screens.list.mvp.DepartmentListScreenPresenter;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Department;

import java.util.List;

public class DepartmentListScreen extends AbsListScreen<DepartmentListScreenPresenter, DepartmentFiltersList, Department> {

    private final String URL = "department_list";

    public DepartmentListScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator);
        screenPresenter.getAllDepartment();
    }

    @Override
    public void onSearchQueryTextChanged(String text) {

    }

    @Override
    public void onSearchIconClick() {
        screenPresenter.getDepartmentFilterByName(listScreenHeader.getSearchQuery());
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public void onListReady(List<Department> list) {
        listGrid.setItems(list);
    }

    @Override
    protected void addGridColumn() {
        listGrid.addColumn(Department::getShortName).setCaption(R.Strings.SHORT_NAME).setResizable(true);
        listGrid.addColumn(Department::getFullName).setCaption(R.Strings.FULL_NAME).setResizable(true);
        listGrid.addColumn(department -> department.getInstitution().getShortName()).setCaption(R.Strings.INSTITUTION).setResizable(true);
    }

    @Override
    protected DepartmentFiltersList getFiltersView() {
        DepartmentFiltersList departmentFiltersList = new DepartmentFiltersList();
        departmentFiltersList.setDepartmentFilterStateListener(new DepartmentFiltersList.OnDepartmentFilterStateListener() {
            @Override
            public void onDepartmentFilterApply(String institution) {
                screenPresenter.getDepartmentFilterByInstitution(institution);
            }

            @Override
            public void onDepartmentFilterClear() {
                screenPresenter.getAllDepartment();
            }
        });
        return departmentFiltersList;
    }

    @Override
    protected DepartmentListScreenPresenter getScreenPresenter() {
        return new DepartmentListScreenPresenter(this, screenNavigator.getDepartmentService());
    }
}
