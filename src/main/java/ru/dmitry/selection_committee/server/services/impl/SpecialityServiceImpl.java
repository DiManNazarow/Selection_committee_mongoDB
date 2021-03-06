package ru.dmitry.selection_committee.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dmitry.selection_committee.server.models.Institution;
import ru.dmitry.selection_committee.server.models.Speciality;
import ru.dmitry.selection_committee.server.repositories.SpecialityRepository;
import ru.dmitry.selection_committee.server.services.SpecialityService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public String insert(Speciality speciality) {
        return specialityRepository.save(speciality).id;
    }

    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    @Override
    public List<String> getSpecialityNames() {
        List<String> names = new ArrayList<>();
        specialityRepository.findAll().forEach(institution -> names.add(institution.getName()));
        return names;
    }

    @Override
    public Speciality findByName(String name) {
        return specialityRepository.findByName(name);
    }

    @Override
    public Speciality findByCode(String code) {
        return specialityRepository.findByCode(code);
    }

    @Override
    public List<String> getSpecialityNamesWithCodeFilteredByInstitution(Institution institution) {
        List<String> names = new ArrayList<>();
        for (Speciality speciality : getAll().stream().filter(speciality -> speciality.getPulpit().getDepartment().getInstitution().getId().equals(institution.getId())).collect(Collectors.toList())){
            names.add(String.format("%s %s", speciality.getCode(), speciality.getName()));
        }
        return names;
    }

    @Override
    public List<String> getSpecialityNamesWithCode() {
        List<String> names = new ArrayList<>();
        for (Speciality speciality : specialityRepository.findAll()){
            names.add(String.format("%s %s", speciality.getCode(), speciality.getName()));
        }
        return names;
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByName(String name) {
        return specialityRepository.findByNameContaining(name);
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByInstitutionAndDepartmentAndPulpit(String institution, String department, String pulpit) {
        return specialityRepository.findAll().stream().filter(speciality ->
                (speciality.getPulpit().getDepartment().getInstitution().getShortName().contains(institution) || speciality.getPulpit().getDepartment().getInstitution().getFullName().contains(institution)) &&
                        (speciality.getPulpit().getDepartment().getShortName().contains(department) || speciality.getPulpit().getDepartment().getFullName().contains(department)) &&
                        (speciality.getPulpit().getShortName().contains(pulpit) || speciality.getPulpit().getFullName().contains(pulpit))).collect(Collectors.toList());
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByInstitutionAndDepartment(String institution, String department) {
        return specialityRepository.findAll().stream().filter(speciality ->
                (speciality.getPulpit().getDepartment().getInstitution().getFullName().contains(institution) || speciality.getPulpit().getDepartment().getInstitution().getShortName().contains(institution)) &&
                        (speciality.getPulpit().getDepartment().getFullName().contains(department) || speciality.getPulpit().getDepartment().getShortName().contains(department))).collect(Collectors.toList());
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByInstitutionAndPulpit(String institution, String pulpit) {
        return specialityRepository.findAll().stream().filter(speciality ->
                (speciality.getPulpit().getDepartment().getInstitution().getShortName().contains(institution) || speciality.getPulpit().getDepartment().getInstitution().getFullName().contains(institution)) &&
                        (speciality.getPulpit().getShortName().contains(pulpit) || speciality.getPulpit().getFullName().contains(pulpit))).collect(Collectors.toList());
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByDepartmentAndPulpit(String department, String pulpit) {
        return specialityRepository.findAll().stream().filter(speciality ->
                (speciality.getPulpit().getDepartment().getShortName().contains(department) || speciality.getPulpit().getDepartment().getFullName().contains(department)) &&
                        (speciality.getPulpit().getShortName().contains(pulpit) || speciality.getPulpit().getFullName().contains(pulpit))).collect(Collectors.toList());
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByInstitution(String institution) {
        return specialityRepository.findAll().stream().filter(speciality -> speciality.getPulpit().getDepartment().getInstitution().getFullName().contains(institution) || speciality.getPulpit().getDepartment().getInstitution().getShortName().contains(institution)).collect(Collectors.toList());
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByDepartment(String department) {
        return specialityRepository.findAll().stream().filter(speciality -> speciality.getPulpit().getDepartment().getFullName().contains(department) || speciality.getPulpit().getDepartment().getShortName().contains(department)).collect(Collectors.toList());
    }

    @Override
    public List<Speciality> getSpecialitiesFilteredByPulpit(String pulpit) {
        return specialityRepository.findAll().stream().filter(speciality -> speciality.getPulpit().getShortName().contains(pulpit) || speciality.getPulpit().getFullName().contains(pulpit)).collect(Collectors.toList());
    }

    @Override
    public void delete(Speciality speciality) {
        specialityRepository.delete(speciality);
    }
}
