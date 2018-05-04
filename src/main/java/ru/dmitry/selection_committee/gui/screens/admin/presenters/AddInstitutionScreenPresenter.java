package ru.dmitry.selection_committee.gui.screens.admin.presenters;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.services.InstitutionService;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class AddInstitutionScreenPresenter extends BasePresenter<AddInstitutionScreenView> {

    private InstitutionService institutionService;

    private String shortName;

    private String fullName;

    private String city;

    private String street;

    private String house;

    public AddInstitutionScreenPresenter(AddInstitutionScreenView mvpView, InstitutionService institutionService) {
        super(mvpView);
        this.institutionService = institutionService;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void add(){
        Institution institution = new Institution();
        institution.setFullName(fullName);
        institution.setShortName(shortName);
        institution.setCity(city);
        institution.setHome(house);
        institution.setStreet(street);
        String id = institutionService.insert(institution);
        if (!AppTextUtils.isTextEmpty(id)){
            getViewState().onSuccessAdded();
        } else {
            getViewState().onFailAdded();
        }
    }

}
