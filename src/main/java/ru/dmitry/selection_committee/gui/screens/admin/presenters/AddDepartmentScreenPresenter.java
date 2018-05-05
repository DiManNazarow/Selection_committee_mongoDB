package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Department;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.services.DepartmentService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class AddDepartmentScreenPresenter extends BasePresenter<AddDepartmentScreenView> {

    private String shortName;

    private String fullName;

    private Institution institution;

    private DepartmentService departmentService;

    public AddDepartmentScreenPresenter(AddDepartmentScreenView mvpView, DepartmentService departmentService) {
        super(mvpView);
        this.departmentService = departmentService;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public void add(){
        if (AppTextUtils.isTextEmpty(shortName)){
            getViewState().onShortNameEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(fullName)){
            getViewState().onFullNameEmpty();
            return;
        }
        if (institution == null){
            getViewState().onInstitutionNotSelected();
            return;
        }
        Department department = new Department();
        department.setShortName(shortName);
        department.setFullName(fullName);
        department.setInstitutionId(institution.id);
        String id = departmentService.insert(department);
        if (!AppTextUtils.isTextEmpty(id)){
            getViewState().onSuccessAdded();
        } else {
            getViewState().onFailAdded();
        }
    }

}
