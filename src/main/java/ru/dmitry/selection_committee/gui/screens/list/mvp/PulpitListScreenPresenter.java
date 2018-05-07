package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Pulpit;
import ru.dmitry.selection_committee.server.services.PulpitService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class PulpitListScreenPresenter extends BasePresenter<ListScreenView<Pulpit>> {

    private PulpitService pulpitService;

    public PulpitListScreenPresenter(ListScreenView<Pulpit> mvpView, PulpitService pulpitService) {
        super(mvpView);
        this.pulpitService = pulpitService;
    }

    public void getAllPulpit(){
        getViewState().onListReady(pulpitService.getAll());
    }

    public void getPulpitFilteredByName(String name){
        getViewState().onListReady(pulpitService.findByName(name));
    }

    public void getPulpitFilteredByInstitutionAndDeparture(String institution, String departure){
        if (!AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(departure)) {
            getViewState().onListReady(pulpitService.findByInstitutionAndDepartment(institution, departure));
        } else if (AppTextUtils.isTextEmpty(departure)){
            getPulpitFilteredByInstitution(institution);
        } else if (AppTextUtils.isTextEmpty(institution)){
            getPulpitFilteredByDeparture(departure);
        }
    }

    private void getPulpitFilteredByInstitution(String institution){
        getViewState().onListReady(pulpitService.findByInstitution(institution));
    }

    private void getPulpitFilteredByDeparture(String departure){
        getViewState().onListReady(pulpitService.findByDepartment(departure));
    }

}
