package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Pulpit;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.services.PulpitService;
import ru.dmitry.selection_committee.server.services.SpecialityService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.util.Collections;

public class AddSpecialityScreenPresenter extends BasePresenter<AddSpecialityScreenView> {

    private String name;

    private String code;

    private Pulpit pulpit;

    private SpecialityService specialityService;

    private PulpitService pulpitService;

    public AddSpecialityScreenPresenter(AddSpecialityScreenView mvpView, SpecialityService specialityService, PulpitService pulpitService) {
        super(mvpView);
        this.specialityService = specialityService;
        this.pulpitService = pulpitService;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPulpit(Pulpit pulpit) {
        this.pulpit = pulpit;
    }

    public void add(){
        if (AppTextUtils.isTextEmpty(name)){
            getViewState().onFullNameEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(code)){
            getViewState().onCodeEmpty();
            return;
        }
        if (pulpit == null){
            getViewState().onReferenceNotSelected();
            return;
        }
        Speciality speciality = new Speciality();
        speciality.setName(name);
        speciality.setCode(code);
        speciality.setPulpitId(pulpit.getId());
        String id = specialityService.insert(speciality);
        if (!AppTextUtils.isTextEmpty(id)){
            speciality.setId(id);
            if (pulpit.getSpecialities() == null || pulpit.getSpecialities().isEmpty()){
                pulpit.setSpecialities(Collections.singletonList(speciality));
            } else {
                pulpit.getSpecialities().add(speciality);
            }
            pulpitService.insert(pulpit);
            getViewState().onSuccessAdded();
        } else {
            getViewState().onFailAdded();
        }
    }

}
