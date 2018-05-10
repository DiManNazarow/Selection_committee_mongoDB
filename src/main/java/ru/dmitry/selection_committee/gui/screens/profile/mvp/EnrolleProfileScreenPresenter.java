package ru.dmitry.selection_committee.gui.screens.profile.mvp;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.models.Status;
import ru.dmitry.selection_committee.server.services.UserServices;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.util.List;

public class EnrolleProfileScreenPresenter extends BasePresenter<EnrolleProfileScreenView> {

    private String firstName;

    private String secondName;

    private String patronymic;

    private String dateBirth;

    private String pasportNumber;

    private String phoneNumber;

    private String city;

    private String street;

    private String house;

    private String flat;

    private String attestatNumber;

    private String schoolName;

    private List<Speciality> specialities;

    private Status status = Status.ENTERED;

    private UserServices userServices;

    private Enrollee enrollee;

    public EnrolleProfileScreenPresenter(EnrolleProfileScreenView mvpView, UserServices userServices, Enrollee enrollee) {
        super(mvpView);
        this.userServices = userServices;
        this.enrollee = enrollee;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public void setPasportNumber(String pasportNumber) {
        this.pasportNumber = pasportNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public void setAttestatNumber(String attestatNumber) {
        this.attestatNumber = attestatNumber;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void save(){
        if (AppTextUtils.isTextEmpty(firstName)){
            getViewState().onFirstNameEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(secondName)){
            getViewState().onSecondNameEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(patronymic)){
            getViewState().onPatronymicEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(dateBirth)){
            getViewState().onDateBirthEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(pasportNumber)){
            getViewState().onPasportNameEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(phoneNumber)){
            getViewState().onPhoneNumberEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(city)){
            getViewState().onCityEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(street)){
            getViewState().onStreetEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(house)){
            getViewState().onHouseEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(attestatNumber)){
            getViewState().onAttestatEmpty();
            return;
        }
        if (AppTextUtils.isTextEmpty(schoolName)){
            getViewState().onSchoolEmpty();
            return;
        }
        if (specialities == null || specialities.isEmpty()){
            getViewState().onSpecialityNotChosen();
            return;
        }
        if (enrollee != null) {
            enrollee.setFirstName(firstName);
            enrollee.setSecondName(secondName);
            enrollee.setPatronymic(patronymic);
            enrollee.setDateBirth(dateBirth);
            enrollee.setPassportNumber(pasportNumber);
            enrollee.setPhone(phoneNumber);
            enrollee.setCity(city);
            enrollee.setStreet(street);
            enrollee.setHouse(house);
            enrollee.setFlat(flat);
            enrollee.setAttestatNumber(attestatNumber);
            enrollee.setSchoolName(schoolName);
            enrollee.setSpecialities(specialities);
            enrollee.setStatus(status.getCode());

            String id = userServices.insertUser(enrollee);
            if (!AppTextUtils.isTextEmpty(id)) {
                getViewState().onProfileSuccessEdit();
            } else {
                getViewState().onProfileEditFailed();
            }
        }
    }

    public void signOut(){
        getViewState().onSignOut();
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }
}
