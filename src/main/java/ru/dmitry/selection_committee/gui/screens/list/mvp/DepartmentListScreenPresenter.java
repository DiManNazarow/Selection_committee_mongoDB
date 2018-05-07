package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Department;
import ru.dmitry.selection_committee.server.services.DepartmentService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class DepartmentListScreenPresenter extends BasePresenter<ListScreenView<Department>> {

    private DepartmentService departmentService;

    public DepartmentListScreenPresenter(ListScreenView<Department> mvpView, DepartmentService departmentService) {
        super(mvpView);
        this.departmentService = departmentService;
    }

    public void getAllDepartment(){
        getViewState().onListReady(departmentService.getAll());
    }

    public void getDepartmentFilterByName(String name){
        if (!AppTextUtils.isTextEmpty(name)){
            getViewState().onListReady(departmentService.findByName(name));
        } else {
            getAllDepartment();
        }
    }

    public void getDepartmentFilterByInstitution(String institutionName){
        if (!AppTextUtils.isTextEmpty(institutionName)){
            getViewState().onListReady(departmentService.findByInstitutionName(institutionName));
        } else {
            getAllDepartment();
        }
    }

}
