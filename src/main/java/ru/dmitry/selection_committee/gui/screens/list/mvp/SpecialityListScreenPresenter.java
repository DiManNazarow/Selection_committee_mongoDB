package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.services.SpecialityService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class SpecialityListScreenPresenter extends BasePresenter<ListScreenView<Speciality>> {

    private SpecialityService specialityService;

    public SpecialityListScreenPresenter(ListScreenView<Speciality> mvpView, SpecialityService specialityService) {
        super(mvpView);
        this.specialityService = specialityService;
    }

    public void getAllSpecialities(){
        getViewState().onListReady(specialityService.getAll());
    }

    public void getSpecialitiesFilteredByName(String name){
        getViewState().onListReady(specialityService.getSpecialitiesFilteredByName(name));
    }

    public void getSpecialitiesFilteredByInstitutionAndDepartmentAndPulpit(String institution, String department, String pulpit){
        if (!AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(department) && !AppTextUtils.isTextEmpty(pulpit)){
            getViewState().onListReady(specialityService.getSpecialitiesFilteredByInstitutionAndDepartmentAndPulpit(institution, department, pulpit));
        } else if (!AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(department) && AppTextUtils.isTextEmpty(pulpit)){
            getSpecialitiesFilteredByInstitutionAndDepartment(institution, department);
        } else if (AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(department) && !AppTextUtils.isTextEmpty(pulpit)){
            getSpecialitiesFilteredByDepartmentAndPulpit(department, pulpit);
        } else if (!AppTextUtils.isTextEmpty(institution) && AppTextUtils.isTextEmpty(department) && !AppTextUtils.isTextEmpty(pulpit)){
            getSpecialitiesFilteredByInstitutionAndPulpit(institution, pulpit);
        } else if (!AppTextUtils.isTextEmpty(institution) && AppTextUtils.isTextEmpty(department) && AppTextUtils.isTextEmpty(pulpit)){
            getSpecialitiesFilteredByInstitution(institution);
        } else if (AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(department) && AppTextUtils.isTextEmpty(pulpit)){
            getSpecialitiesFilteredByDepartment(department);
        } else if (AppTextUtils.isTextEmpty(institution) && AppTextUtils.isTextEmpty(department) && !AppTextUtils.isTextEmpty(pulpit)){
            getSpecialitiesFilteredByPulpit(pulpit);
        }
    }

    private void getSpecialitiesFilteredByInstitutionAndDepartment(String institution, String department){
        getViewState().onListReady(specialityService.getSpecialitiesFilteredByInstitutionAndDepartment(institution, department));
    }

    private void getSpecialitiesFilteredByInstitutionAndPulpit(String institution, String pulpit){
        getViewState().onListReady(specialityService.getSpecialitiesFilteredByInstitutionAndPulpit(institution, pulpit));
    }

    private void getSpecialitiesFilteredByDepartmentAndPulpit(String department, String pulpit){
        getViewState().onListReady(specialityService.getSpecialitiesFilteredByDepartmentAndPulpit(department, pulpit));
    }

    private void getSpecialitiesFilteredByInstitution(String institution){
        getViewState().onListReady(specialityService.getSpecialitiesFilteredByInstitution(institution));
    }

    private void getSpecialitiesFilteredByDepartment(String department){
        getViewState().onListReady(specialityService.getSpecialitiesFilteredByDepartment(department));
    }

    private void getSpecialitiesFilteredByPulpit(String pulpit){
        getViewState().onListReady(specialityService.getSpecialitiesFilteredByPulpit(pulpit));
    }

    public void delete(Speciality speciality){
        specialityService.delete(speciality);
    }

}
