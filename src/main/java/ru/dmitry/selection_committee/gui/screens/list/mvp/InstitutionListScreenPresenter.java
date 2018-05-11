package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.services.InstitutionService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class InstitutionListScreenPresenter extends BasePresenter<ListScreenView<Institution>> {

    private InstitutionService institutionService;

    public InstitutionListScreenPresenter(ListScreenView<Institution> mvpView, InstitutionService institutionService) {
        super(mvpView);
        this.institutionService = institutionService;
    }

    public void getAllInstitution(){
        getViewState().onListReady(institutionService.getAll());
    }

    public void getInstitutionsFilterByCity(String city){
        if (!AppTextUtils.isTextEmpty(city)) {
            getViewState().onListReady(institutionService.filterByCity(city));
        } else {
            getViewState().onListReady(institutionService.getAll());
        }
    }

    public void getInstitutionsFilterByName(String name){
        if (!AppTextUtils.isTextEmpty(name)) {
            getViewState().onListReady(institutionService.filterByShortNameOrFullName(name));
        } else {
            getViewState().onListReady(institutionService.getAll());
        }
    }

    public void delete(Institution institution){
        institutionService.delete(institution);
        getAllInstitution();
    }

}
