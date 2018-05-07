package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Department;
import ru.dmitry.selection_committee.server.models.Pulpit;
import ru.dmitry.selection_committee.server.services.DepartmentService;
import ru.dmitry.selection_committee.server.services.PulpitService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.util.Collections;

public class AddPulpitScreenPresenter extends BasePresenter<AddScreenView> {

    private String shortName;

    private String fullName;

    private Department department;

    private DepartmentService departmentService;

    private PulpitService pulpitService;

    public AddPulpitScreenPresenter(AddScreenView mvpView, PulpitService pulpitService, DepartmentService departmentService) {
        super(mvpView);
        this.pulpitService = pulpitService;
        this.departmentService = departmentService;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        if (department == null){
            getViewState().onReferenceNotSelected();
            return;
        }
        Pulpit pulpit = new Pulpit();
        pulpit.setShortName(shortName);
        pulpit.setFullName(fullName);
        pulpit.setDepartment(department);
        String id = pulpitService.insert(pulpit);
        if (!AppTextUtils.isTextEmpty(id)){
            pulpit.setId(id);
            if (department.getPulpits() == null || department.getPulpits().isEmpty()){
                department.setPulpits(Collections.singletonList(pulpit));
            } else {
                department.getPulpits().add(pulpit);
            }
            departmentService.insert(department);
            getViewState().onSuccessAdded();
        } else {
            getViewState().onFailAdded();
        }

    }

}
