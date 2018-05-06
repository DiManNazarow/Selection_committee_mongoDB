package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.services.InstitutionService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class InstitutionListScreenPresenter extends BasePresenter<InstitutionListScreenView> {

    private InstitutionService institutionService;

    public InstitutionListScreenPresenter(InstitutionListScreenView mvpView, InstitutionService institutionService) {
        super(mvpView);
        this.institutionService = institutionService;
    }

    public void getAllInstitution(){
        getViewState().onInstitutionListReady(institutionService.getAll());
    }

    public void getInstitutionsFilterByCity(String city){
        if (!AppTextUtils.isTextEmpty(city)) {
            getViewState().onInstitutionListReady(institutionService.filterByCity(city));
        } else {
            getViewState().onInstitutionListReady(institutionService.getAll());
        }
    }

    public void getInstitutionsFilterByName(String name){
        if (!AppTextUtils.isTextEmpty(name)) {
            getViewState().onInstitutionListReady(institutionService.filterByShortNameOrFullName(name));
        } else {
            getViewState().onInstitutionListReady(institutionService.getAll());
        }
    }

}
