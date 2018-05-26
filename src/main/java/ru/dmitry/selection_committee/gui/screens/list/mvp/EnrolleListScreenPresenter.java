package ru.dmitry.selection_committee.gui.screens.list.mvp;

import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.services.UserServices;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.util.ArrayList;
import java.util.List;

public class EnrolleListScreenPresenter extends BasePresenter<ListScreenView<Enrollee>> {

    private UserServices userServices;

    public EnrolleListScreenPresenter(ListScreenView<Enrollee> mvpView, UserServices userServices) {
        super(mvpView);
        this.userServices = userServices;
    }

    public void getAllEnrolle(){
        getViewState().onListReady(userServices.getAllEnrolle());
    }

    public void getAllEnrolleForYear(int year){
        getViewState().onListReady(userServices.getEnrolleByEnteredYear(year));
    }

    public void getEnrolleFilteredByName(String name){
        getViewState().onListReady(userServices.getEnrollesByName(name));
    }

    public void filter(String institution, String speciality, int year){
        if (!AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(speciality) && year != -1){
            getEnrolleFilteredByInstitutionAndSpecialityAndYear(institution, speciality, year);
        } else if (!AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(speciality) && year == -1){
            getEnrolleFilteredByInstitutionAndSpeciality(institution, speciality);
        } else if (!AppTextUtils.isTextEmpty(institution) && AppTextUtils.isTextEmpty(speciality) && year != -1){
            getEnrolleFilteredByInstitutionAndYear(institution, year);
        } else if (!AppTextUtils.isTextEmpty(institution) && AppTextUtils.isTextEmpty(speciality) && year == -1){
            getEnrollFilteredByInstitution(institution);
        } else if (AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(speciality) && year != -1){
            getEnrolleFilteredBySpecialityAndYaer(speciality, year);
        } else if (AppTextUtils.isTextEmpty(institution) && !AppTextUtils.isTextEmpty(speciality) && year == -1){
            getEnrollFilteredBySpeciality(speciality);
        } else if (AppTextUtils.isTextEmpty(institution) && AppTextUtils.isTextEmpty(speciality) && year != -1){
            getAllEnrolleForYear(year);
        } else {
            getAllEnrolle();
        }
    }

    private void getEnrolleFilteredByInstitutionAndSpecialityAndYear(String institution, String speciality, int year){
        List<Enrollee> enrollees = userServices.getEnrolleByEnteredYear(year);
        List<Enrollee> filteredEnrolle = new ArrayList<>();
        for (Enrollee enrollee : enrollees){
            for (Speciality enrolleSpec : enrollee.getSpecialities()){
                if ((enrolleSpec.getPulpit().getDepartment().getInstitution().getShortName().contains(institution) || enrolleSpec.getPulpit().getDepartment().getInstitution().getFullName().contains(institution) &&
                        (enrolleSpec.getName().contains(speciality)))){
                    filteredEnrolle.add(enrollee);
                    break;
                }
            }
        }
        getViewState().onListReady(filteredEnrolle);
    }

    private void getEnrolleFilteredByInstitutionAndSpeciality(String institution, String speciality){
        List<Enrollee> enrollees = userServices.getAllEnrolle();
        List<Enrollee> filteredEnrolle = new ArrayList<>();
        for (Enrollee enrollee : enrollees){
            for (Speciality enrolleSpec : enrollee.getSpecialities()){
                if ((enrolleSpec.getPulpit().getDepartment().getInstitution().getShortName().contains(institution) || enrolleSpec.getPulpit().getDepartment().getInstitution().getFullName().contains(institution) &&
                        (enrolleSpec.getName().contains(speciality)))){
                    filteredEnrolle.add(enrollee);
                    break;
                }
            }
        }
        getViewState().onListReady(filteredEnrolle);
    }

    private void getEnrolleFilteredByInstitutionAndYear(String institution, int year){
        List<Enrollee> enrollees = userServices.getEnrolleByEnteredYear(year);
        List<Enrollee> filteredEnrolle = new ArrayList<>();
        for (Enrollee enrollee : enrollees){
            for (Speciality enrolleSpec : enrollee.getSpecialities()){
                if ((enrolleSpec.getPulpit().getDepartment().getInstitution().getShortName().contains(institution) || enrolleSpec.getPulpit().getDepartment().getInstitution().getFullName().contains(institution))){
                    filteredEnrolle.add(enrollee);
                    break;
                }
            }
        }
        getViewState().onListReady(filteredEnrolle);
    }

    private void getEnrollFilteredByInstitution(String institution){
        List<Enrollee> enrollees = userServices.getAllEnrolle();
        List<Enrollee> filteredEnrolle = new ArrayList<>();
        for (Enrollee enrollee : enrollees){
            for (Speciality enrolleSpec : enrollee.getSpecialities()){
                if ((enrolleSpec.getPulpit().getDepartment().getInstitution().getShortName().contains(institution) || enrolleSpec.getPulpit().getDepartment().getInstitution().getFullName().contains(institution))){
                    filteredEnrolle.add(enrollee);
                    break;
                }
            }
        }
        getViewState().onListReady(filteredEnrolle);
    }

    private void getEnrolleFilteredBySpecialityAndYaer(String speciality, int year){
        List<Enrollee> enrollees = userServices.getEnrolleByEnteredYear(year);
        List<Enrollee> filteredEnrolle = new ArrayList<>();
        for (Enrollee enrollee : enrollees){
            for (Speciality enrolleSpec : enrollee.getSpecialities()){
                if (enrolleSpec.getName().contains(speciality)){
                    filteredEnrolle.add(enrollee);
                    break;
                }
            }
        }
        getViewState().onListReady(filteredEnrolle);
    }

    private void getEnrollFilteredBySpeciality(String speciality){
        List<Enrollee> enrollees = userServices.getAllEnrolle();
        List<Enrollee> filteredEnrolle = new ArrayList<>();
        for (Enrollee enrollee : enrollees){
            for (Speciality enrolleSpec : enrollee.getSpecialities()){
                if (enrolleSpec.getName().contains(speciality)){
                    filteredEnrolle.add(enrollee);
                    break;
                }
            }
        }
        getViewState().onListReady(filteredEnrolle);
    }

    public void delete(Enrollee enrollee){
        userServices.deleteEnrolle(enrollee);
        getAllEnrolle();
    }

}
