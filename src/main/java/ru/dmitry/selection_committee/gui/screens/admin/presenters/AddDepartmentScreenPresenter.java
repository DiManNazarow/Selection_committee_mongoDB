package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Department;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.services.DepartmentService;
import ru.dmitry.selection_committee.server.services.InstitutionService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.util.Collections;

/**
 * Класс представителя (презентера) экрана добавления факультета
 */
public class AddDepartmentScreenPresenter extends BasePresenter<AddScreenView> {

    private String shortName;

    private String fullName;

    private Institution institution;

    private DepartmentService departmentService;

    private InstitutionService institutionService;

    public AddDepartmentScreenPresenter(AddScreenView mvpView, DepartmentService departmentService, InstitutionService institutionService) {
        super(mvpView);
        this.departmentService = departmentService;
        this.institutionService = institutionService;
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
            getViewState().onReferenceNotSelected();
            return;
        }
        Department department = new Department();
        department.setShortName(shortName);
        department.setFullName(fullName);
        department.setInstitution(institution);
        department.setInstitution(institution);
        String id = departmentService.insert(department);
        if (!AppTextUtils.isTextEmpty(id)){
            department.setId(id);
            if (institution.getDepartments() == null || institution.getDepartments().isEmpty()){
                institution.setDepartments(Collections.singletonList(department));
            } else {
                institution.getDepartments().add(department);
            }
            institutionService.insert(institution);
            getViewState().onSuccessAdded();
        } else {
            getViewState().onFailAdded();
        }
    }

}
