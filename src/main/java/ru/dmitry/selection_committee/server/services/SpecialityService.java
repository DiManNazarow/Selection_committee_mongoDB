package ru.dmitry.selection_committee.server.services;

import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.models.Pulpit;
import ru.dmitry.selection_committee.server.models.Speciality;

import java.util.List;

public interface SpecialityService {

    String insert(Speciality speciality);

    List<Speciality> getAll();

    List<String> getSpecialityNames();

    Speciality findByName(String name);

    Speciality findByCode(String code);

    List<String> getSpecialityNamesWithCodeFilteredByInstitution(Institution institution);

    List<String> getSpecialityNamesWithCode();

    List<Speciality> getSpecialitiesFilteredByName(String name);

    List<Speciality> getSpecialitiesFilteredByInstitutionAndDepartmentAndPulpit(String institution, String department, String pulpit);

    List<Speciality> getSpecialitiesFilteredByInstitutionAndDepartment(String institution, String department);

    List<Speciality> getSpecialitiesFilteredByInstitutionAndPulpit(String institution, String pulpit);

    List<Speciality> getSpecialitiesFilteredByDepartmentAndPulpit(String department, String pulpit);

    List<Speciality> getSpecialitiesFilteredByInstitution(String institution);

    List<Speciality> getSpecialitiesFilteredByDepartment(String department);

    List<Speciality> getSpecialitiesFilteredByPulpit(String pulpit);

    void delete(Speciality speciality);

}
